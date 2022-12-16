package modern_java.chapter_16.discount;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import modern_java.chapter_16.shop.ShopVer1;

public class DiscountableShopSearch {
    List<DiscountableShop> shops = List.of(
            new DiscountableShop("BestPrice"),
            new DiscountableShop("LetsSaveBig"),
            new DiscountableShop("MyFavoriteShop"),
            new DiscountableShop("HiBye"),
            new DiscountableShop("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);  // 프로그램 종료를 방해하지 않는 데몬스레드 사용
                    return thread;
                }
            });

    /**
     * thenApply : CompletableFuture가 끝날 때까지 블록하지 않음
     * - 즉, CompletableFuture가 동작을 완전히 완료한 다음에 thenApply 메서드로 전달된 람다식을 적용할 수 있음
     * - 즉, thenApply는 하나의 CompletableFuture에서 작동한다.
     * <p>
     * thenCompose : 첫 번째 연산의 결과를 두 번째 연산으로 전달
     * - 즉, 첫 번째 CompletableFuture에 thenCompose 메서드를 호출하고 Function에 넘겨주는 식으로 두번째 CompletableFuture를 조합한다.
     * - Function은 첫 번째 CompletableFuture의 반환 결과를 인수로 받고 두 번째 CompletableFuture를 반환한다.
     * - 두 번째 CompletableFuture는 첫 번째 CompletableFuture의 결과를 연산의 입력으로 사용한다.
     * <p>
     * 메서드 뒤에 xxxAsync()를 붙이면 별도의 태스크에서 수행하게 된다.
     * - 별도의 태스크에서 수행할 필요가 없다면 붙이지 말자.
     */
    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }


    enum Money {
        EUR, USD
    }

    static double getRate(Money m1, Money m2) {
        return 0.89;
    }

    public static void main(String[] args) {
        //TODO 독립적인 두 태스크를 합치기
        ShopVer1 shop = new ShopVer1("shop");
        Future<Double> futurePriceInUSD = CompletableFuture.supplyAsync(
                        () -> shop.getPrice("product"))
                .thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> getRate(Money.EUR, Money.USD)),
                        (price, rate) -> price * rate
                );

        //TODO 타임 아웃
        CompletableFuture<Double> futurePriceUSDTimeout = CompletableFuture.supplyAsync(
                        () -> shop.getPrice("product"))
                .thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> getRate(Money.EUR, Money.USD)),
                        (price, rate) -> price * rate
                )
                .orTimeout(3, TimeUnit.SECONDS);

        //TODO 환전 서비스가 1초 안에 결과를 제공하지 않으면 기본 환율값 사용
        double DEFAULT_RATE = 1.0;
        CompletableFuture.supplyAsync(
                        () -> shop.getPrice("product"))
                .thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> getRate(Money.EUR, Money.USD))
                                .completeOnTimeout(DEFAULT_RATE, 1, TimeUnit.SECONDS),
                        (price, rate) -> price * rate
                )
                .orTimeout(3, TimeUnit.SECONDS);

    }
}
