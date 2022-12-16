package modern_java.chapter_16.search;

import static java.util.stream.Collectors.toList;

import java.util.List;
import modern_java.chapter_16.shop.ShopVer1;

/**
 * 제어할 수 없는 동기 방식의 블록 메서드를 사용할 수 밖에 없는 상황에서, 비동기적으로 여러 API에 질의하는 방법
 * - 즉, 한 요청의 응답을 기다리며 블록하는 상황을 피하는 방법을 알아보자
 */
public class SearchShopVer1 {
    List<ShopVer1> shops = List.of(
            new ShopVer1("BestPrice"),
            new ShopVer1("LetsSaveBig"),
            new ShopVer1("MyFavoriteShop"),
            new ShopVer1("HiBye"),
            new ShopVer1("BuyItAll"));

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shopVer1 -> String.format("%s price is %.2f",
                        shopVer1.getName(), shopVer1.getPrice(product)))
                .collect(toList());
    }
}
