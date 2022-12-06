package modern_java.chapter_08;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class _02_Quiz {

    public static void main(String[] args) {
        HashMap<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

//        Map<String, Integer> collect = movies.entrySet().stream()
//                .filter(entry -> entry.getValue() >= 10)
//                .collect(Collectors.toMap(
//                        Entry::getKey,
//                        Entry::getValue
//                ));
//
//        System.out.println(collect);

        Set<Entry<String, Integer>> entries = movies.entrySet();  // Entry도 Set이니까 removeIf가 있다!!!!!!
        entries.removeIf(entry -> entry.getValue() < 10);

        System.out.println(movies);
    }
}
