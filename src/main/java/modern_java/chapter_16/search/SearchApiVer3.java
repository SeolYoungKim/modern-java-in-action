package modern_java.chapter_16.search;

import java.util.List;

public class SearchApiVer3 {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        SearchShopVer3 searchShopVer3 = new SearchShopVer3();
        List<String> results = searchShopVer3.findPrices("IPhone13");
        System.out.println(results);

        long after = System.currentTimeMillis();
        System.out.println("Done in " + (after - before) + " msecs");
    }
}
