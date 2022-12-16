package modern_java.chapter_16.discount;

import static java.util.concurrent.ThreadLocalRandom.current;
import static modern_java.chapter_16.shop.ShopVer2.delay;

import modern_java.chapter_16.discount.Discount.Code;

public class DiscountableShop {
    private final String shopName;

    public DiscountableShop(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice(String product) {
        delay();
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[current().nextInt(Code.values().length)];

        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    private double calculatePrice(String product) {
        return current().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
