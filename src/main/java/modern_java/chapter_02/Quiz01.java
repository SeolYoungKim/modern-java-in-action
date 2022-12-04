package modern_java.chapter_02;

import java.util.List;

public class Quiz01 {

    public static void main(String[] args) {
        List<Apple> apples = List.of(new Apple(50), new Apple(100), new Apple(350));
        prettyPrintApple(apples, new AppleFormatterWeight());
    }

    static class Apple {
        private final int weight;

        public Apple(int weight) {
            this.weight = weight;
        }

        public int weight() {
            return weight;
        }

    }

    interface AppleFormatter {

        String prettyFormat(Apple apple);
    }

    static class AppleFormatterWeight implements AppleFormatter {

        @Override
        public String prettyFormat(Apple apple) {
            return "사과의 무게는 " + apple.weight() + "g 입니다.";
        }
    }

    static class AppleFormatterWeightLightOrHeavy implements AppleFormatter {

        @Override
        public String prettyFormat(Apple apple) {
            if (apple.weight() > 100) {
                return "무거운 사과입니다.";
            }

            return "가벼운 사과입니다.";
        }
    }


    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String format = appleFormatter.prettyFormat(apple);
            System.out.println(format);
        }
    }
}
