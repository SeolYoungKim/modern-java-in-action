package modern_java.chapter_16.shop;

import java.util.concurrent.Future;

/**
 * 예외가 발생한다면 어떻게 될까?
 * - 예외는 해당 스레드에만 영향을 미친다
 * - 즉, 예외가 발생해도 가격 계산은 계속 진행되며, 일의 순서는 꼬인다.
 * - 결과적으로 get메서드가 반환될 때 까지 클라이언트가 영원히 기다리게 될 수도 있다
 */
public class ShopApiVer1 {
    public static void main(String[] args) {
        ShopVer1 shopVer1 = new ShopVer1("BestShop");
        long start = System.nanoTime();

        Future<Double> futurePrice = shopVer1.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        System.out.println("doSomethingElse");
        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
