package modern_java.chapter_16.discount;

import static java.text.NumberFormat.getInstance;
import static modern_java.chapter_16.shop.ShopVer2.delay;

public class Discount {
    public enum Code {
        NONE(0), SLIVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.shopName() + " price is " +
                Discount.apply(quote.price(),
                        quote.discountCode());
    }

    private static String apply(double price, Code discountCode) {
        delay();
        return getInstance().format(price * (100 - discountCode.percentage) / 100);
    }
}
