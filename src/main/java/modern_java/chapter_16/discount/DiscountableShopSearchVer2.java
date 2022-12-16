package modern_java.chapter_16.discount;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class DiscountableShopSearchVer2 {
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

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor
                        )));
    }

    public static void main(String[] args) {
        DiscountableShopSearchVer2 shopSearch = new DiscountableShopSearchVer2();
        CompletableFuture[] futures = shopSearch.findPricesStream("IPhone")
                .map(future -> future.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);

        System.out.println("futures = " + Arrays.toString(futures));
        CompletableFuture.allOf(futures).join();
    }
}
