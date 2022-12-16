package modern_java.chapter_16.discount;

import modern_java.chapter_16.discount.Discount.Code;

public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Code.valueOf(split[2]);

        return new Quote(shopName, price, code);
    }

    public String shopName() {
        return shopName;
    }

    public double price() {
        return price;
    }

    public Code discountCode() {
        return discountCode;
    }
}
