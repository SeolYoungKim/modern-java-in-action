package modern_java.chapter_16.discount;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class DiscountableShopSearch {
    List<DiscountableShop> shops = List.of(
            new DiscountableShop("BestPrice"),
            new DiscountableShop("LetsSaveBig"),
            new DiscountableShop("MyFavoriteShop"),
            new DiscountableShop("HiBye"),
            new DiscountableShop("BuyItAll"));

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }
}
