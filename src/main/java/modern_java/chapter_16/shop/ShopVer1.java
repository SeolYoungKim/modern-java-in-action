package modern_java.chapter_16.shop;

import static java.util.concurrent.ThreadLocalRandom.current;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ShopVer1 {
    private final String shopName;

    public ShopVer1(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);  // 다른 쓰레드에서 계산 수행
            futurePrice.complete(price);  // 계산이 완료될 경우 Future에 값 설정
        }).start();

        return futurePrice;  // 계산 결과가 완료되지 않아도 Future를 반환
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

    public String getName() {
        return shopName;
    }
}
