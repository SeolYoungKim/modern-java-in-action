package modern_java.chapter_16.search;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import modern_java.chapter_16.shop.ShopVer1;

/**
 * 제어할 수 없는 동기 방식의 블록 메서드를 사용할 수 밖에 없는 상황에서, 비동기적으로 여러 API에 질의하는 방법
 * - 즉, 한 요청의 응답을 기다리며 블록하는 상황을 피하는 방법을 알아보자
 */
public class SearchShopVer2 {
    List<ShopVer1> shops = List.of(
            new ShopVer1("BestPrice"),
            new ShopVer1("LetsSaveBig"),
            new ShopVer1("MyFavoriteShop"),
            new ShopVer1("HiBye"),
            new ShopVer1("BuyItAll"));

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shopVer1 -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shopVer1.getName(), shopVer1.getPrice(product))
                ))
                .collect(toList());


        //TODO 하나의 파이프라인으로 처리했다면, 스트림의 lazy evaluation이라는 특성 때문에,
        // 모든 가격 정보 요청 동작이 동기적, 순차적으로 이루어질 것이다. 따라서, 분리해서 순차계산을 회피해야 한다.
        // - CompletableFuture로 각 상점의 정보를 요청할 때, 기존 요청 작업이 완료되어야 join이 결과를 반환하면서 다음 상점으로 정보를 요청할 수 있기 때문!
        // - 분할하지 않았다면, 이전 요청의 처리가 완전히 끝난 다음 새로 만든 CompletableFuture의 join이 처리되었을 것이다.
        // - 두 작업을 분할하면, CompletableFuture를 우선 리스트로 모으고, 다른 작업과는 독립적으로 각자의 작업을 수행할 수 있게 된다.
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
