package modern_java.chapter_16.discount;

import java.util.List;

public class DiscountApi {
    public static void main(String[] args) {
        DiscountableShop shop = new DiscountableShop("BestPrice");
        String gold = shop.getPrice("GOLD");
        System.out.println(gold);

        long before = System.currentTimeMillis();

        DiscountableShopSearch search = new DiscountableShopSearch();
        List<String> prices = search.findPrices("GOLD");
        System.out.println(prices);

        long after = System.currentTimeMillis();

        System.out.println(after - before + " msecs");
    }
}
