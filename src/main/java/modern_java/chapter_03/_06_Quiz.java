package modern_java.chapter_03;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class _06_Quiz {

    public static void main(String[] args) {
        ToIntFunction<String> stringToIntFunction = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;
//        Predicate<String> startsWith = this::startsWithNumber;
    }
}
