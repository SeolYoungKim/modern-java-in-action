package modern_java.chapter_16.shop;

import static java.util.concurrent.ThreadLocalRandom.current;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 예외 처리 개선
 */
public class ShopVer2 {
    private final String shopName;

    public ShopVer2(String shopName) {
        this.shopName = shopName;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                if (product.equals("error")) {
                    throw new IllegalArgumentException("product not available");
                }
                double price = calculatePrice(product);  // 다른 쓰레드에서 계산 수행
                futurePrice.complete(price);  // 계산이 정상적으로 종료될 경우, Future에 가격 정보를 저장한 채로 Future 종료
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);  // 도중에 문제가 발생하면, 발생한 예외를 포함시켜 Future 종료
            }
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
}
