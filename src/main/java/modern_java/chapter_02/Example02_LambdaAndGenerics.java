package modern_java.chapter_02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Example02_LambdaAndGenerics {

    public static void main(String[] args) {
        List<Apple> apples = filterObjects(
                List.of(
                        new Apple(Color.RED, 30), new Apple(Color.GREEN, 100)),
                (Apple apple) -> apple.color == Color.RED
        );

        System.out.println(apples);
    }

    static <T> List<T> filterObjects(List<T> tList, Predicate<T> predicate) {
        List<T> filteredResults = new ArrayList<>();
        for (T t : tList) {
            if (predicate.test(t)) {
                filteredResults.add(t);
            }
        }

        return filteredResults;
    }

    enum Color {
        RED, GREEN
    }

    static class Apple {
        private final Color color;
        private final int weight;

        public Apple(Color color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public Color color() {
            return color;
        }

        public int weight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color=" + color +
                    ", weight=" + weight +
                    '}';
        }
    }
}
