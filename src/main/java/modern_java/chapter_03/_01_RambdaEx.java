package modern_java.chapter_03;

import modern_java.chapter_02.Apple;
import java.nio.file.DirectoryStream.Filter;
import java.util.Comparator;
import java.util.function.Function;

public class _01_RambdaEx {

    public static void main(String[] args) {
        Function<String, Integer> stringToLength = (String s) -> s.length();
        Filter<Apple> appleFilter = (Apple a) -> a.weight() > 150;
//        (int x, int y) -> {
//            System.out.println(x + y);
//        };

//        Comparator<Apple> appleComparator = (Apple a1, Apple a2) -> a1.weight() - a2.weight();
        Comparator<Apple> appleComparator = Comparator.comparingInt(Apple::weight);

        Runnable r1 = () -> System.out.println("HelloWorld!");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("HelloWorld!");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("HI!!!!!!!!!!!!!!!!!!"));

    }

    public static void process(Runnable r) {
        r.run();
    }
}
