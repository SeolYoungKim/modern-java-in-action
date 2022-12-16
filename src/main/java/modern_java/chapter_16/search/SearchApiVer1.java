package modern_java.chapter_16.search;

import java.util.List;

public class SearchApiVer1 {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        SearchShopVer1 searchShopVer1 = new SearchShopVer1();
        List<String> results = searchShopVer1.findPrices("IPhone13");
        System.out.println(results);

        long after = System.currentTimeMillis();
        System.out.println("Done in " + (after - before) + " msecs");
    }
}
