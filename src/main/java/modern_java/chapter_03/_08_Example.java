package modern_java.chapter_03;

import modern_java.chapter_02.Apple;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _08_Example {

    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.weight() - o2.weight();
        }
    }

    public static void main(String[] args) {
        Comparator<Apple> anonymous = new Comparator<>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight() - o2.weight();
            }
        };

//        Comparator<Apple> appleComparator = (a1, a2) -> a1.weight() - a2.weight();
        Comparator<Apple> appleComparator = Comparator.comparingInt(Apple::weight);
        List<Apple> apples = new ArrayList<>(List.of(
                new Apple(),
                new Apple(),
                new Apple()
        ));

//        apples.sort((a1, a2) -> a1.weight() - a2.weight());
        apples.sort(Comparator.comparingInt(Apple::weight));  // apples의 타입을 보고 형식 추론.
    }

}
