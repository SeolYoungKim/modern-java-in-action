package modern_java.chapter_16.search;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import modern_java.chapter_16.shop.ShopVer1;

/**
 * 제어할 수 없는 동기 방식의 블록 메서드를 사용할 수 밖에 없는 상황에서, 비동기적으로 여러 API에 질의하는 방법 - 즉, 한 요청의 응답을 기다리며 블록하는 상황을 피하는
 * 방법을 알아보자
 */
public class SearchShopVer3 {
    private final List<ShopVer1> shops = List.of(
            new ShopVer1("BestPrice"),
            new ShopVer1("LetsSaveBig"),
            new ShopVer1("MyFavoriteShop"),
            new ShopVer1("HiBye"),
            new ShopVer1("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);  // 프로그램 종료를 방해하지 않는 데몬스레드 사용
                    return thread;
                }
            });

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shopVer1 -> CompletableFuture.supplyAsync(
                        () -> format("%s price is %.2f",
                                shopVer1.getName(), shopVer1.getPrice(product)),
                        executor
                ))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
