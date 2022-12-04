package modern_java.chapter_03;

import modern_java.chapter_02.Apple;
import modern_java.chapter_02.Color;
import java.util.function.Function;
import java.util.function.Predicate;

public class _09_Example {

    public static void main(String[] args) {
        Predicate<Apple> redApple = apple -> apple.color() == Color.RED;  // 빨간 사과

        Predicate<Apple> negate = redApple.negate();  // 빨간사과가 아닌 사과
        Predicate<Apple> redAndMoreThan150g = redApple.and(apple -> apple.weight() > 150);  // 빨간색이고, 150그램이 넘는 사과
        Predicate<Apple> orGreen = redAndMoreThan150g
                .or(apple -> apple.weight() < 150); // 빨간색이면서 150그램이 넘는 사과 or 그냥 녹색 사과

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));

        h = f.compose(g);
        System.out.println(h.apply(1));

    }
}
