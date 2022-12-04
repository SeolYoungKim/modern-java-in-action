package modern_java.chapter_06;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import modern_java.Dish;
import modern_java.Menu;

public class _02_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 1.count
        Long counting = dishes.stream().collect(counting());  // 이는 다른 컬렉터와 함께 사용될 때 위력을 발휘함.
        long count = dishes.stream().count();


        //TODO 2. 최댓값, 최솟값
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::calories);
        Optional<Dish> max = dishes.stream().collect(maxBy(dishComparator));
        Optional<Dish> min = dishes.stream().collect(minBy(dishComparator));


        //TODO 3. 요약 연산
        // - summingInt : 객체를 int로 매핑하는 함수를 인수로 받음.
        Integer summingInt = dishes.stream().collect(summingInt(Dish::calories));  // int로 매핑한 컬렉터 반환.
        int sum = dishes.stream().mapToInt(Dish::calories).sum();

        //TODO averaging
        Double average = dishes.stream().collect(averagingInt(Dish::calories));

        //TODO sumarizingInt
        IntSummaryStatistics summaryStatistics = dishes.stream().collect(summarizingInt(Dish::calories));
        System.out.println(summaryStatistics);

        //TODO 4. 문자열 연결
        // - joining
        // - 책은 toString()을 구현하면 된다그러는데... 안되는게 맞다.
        // https://stackoverflow.com/questions/50583490/can-you-collectjoining-without-mapping-to-string
        String menu = dishes.stream().map(Dish::name).collect(joining(","));
        String menu2 = dishes.stream().map(Dish::name).collect(joining(",", "[", "]"));
        System.out.println("menu = " + menu);
        System.out.println("menu2 = " + menu2);


        //TODO 5. 범용 리듀싱 요약 연산
        // - Collectors.reducing
        dishes.stream().collect(reducing(0, Dish::calories, (i, j) -> i + j));
        dishes.stream().collect(reducing((d1, d2) -> d1.calories() > d2.calories() ? d1 : d2));


        //TODO 6. 같은 연산도 다양한 방식으로!
        dishes.stream().collect(reducing(0, Dish::calories, Integer::sum));
        dishes.stream().collect(reducing(0L, dish -> 1L, Long::sum));  // counting

        // 안되는 코드
//        dishes.stream().collect(reducing((d1, d2) -> d1.name() + d2.name()))

    }
}
