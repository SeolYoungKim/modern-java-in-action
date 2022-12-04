package modern_java.chapter_03;

import modern_java.chapter_02.Apple;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

public class _04_Example {

    public static void main(String[] args) {
        Predicate<Apple> applePredicate = (Apple apple) -> apple.weight() > 150;

        Function<BufferedReader, String> f = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
