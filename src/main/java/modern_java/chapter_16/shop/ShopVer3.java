package modern_java.chapter_16.shop;

import static java.util.concurrent.ThreadLocalRandom.current;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * sypplyAsync 팩토리 메서드로 CompletableFutre 만들기
 */
public class ShopVer3 {
    private final String shopName;

    public ShopVer3(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getPriceAsync(String product) {
        // TODO 이는 ForkJoinPool의 Executor중 하나가 실행할 것이다.
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();  //TODO 블록된 상황을 나타냄
        return current().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
