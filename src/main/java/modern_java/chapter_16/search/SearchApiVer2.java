package modern_java.chapter_16.search;

import java.util.List;

public class SearchApiVer2 {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        SearchShopVer2 searchShopVer2 = new SearchShopVer2();
        List<String> results = searchShopVer2.findPrices("IPhone13");
        System.out.println(results);

        long after = System.currentTimeMillis();
        System.out.println("Done in " + (after - before) + " msecs");
    }
}
