package chapter_02;

import java.util.ArrayList;
import java.util.List;

public class Example01_strategyDesignPattern {

    public static void main(String[] args) {
        filterApples(List.of(new Apple()), new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.color() == Color.RED;
            }
        });
    }

    static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate) {  // TODO 이 부분이 동작 파라미터화 된 것이다!
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (applePredicate.test(apple)) {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }

    interface ApplePredicate {  // 알고리즘 패밀리
        boolean test(Apple apple);
    }

    public class AppleHeavyWeightPredicate implements ApplePredicate {  // 전략1

        @Override
        public boolean test(Apple apple) {
            return apple.weight() > 150;
        }
    }

    public class AppleGreenColorPredicate implements ApplePredicate {  // 전략2

        @Override
        public boolean test(Apple apple) {
            return apple.color() == Color.GREEN;
        }
    }

}
