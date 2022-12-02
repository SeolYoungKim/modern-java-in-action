package chapter_05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 매핑
 * map, flatMap 메서드는 특정 데이터를 선택하는 기능을 제공한다.
 * - 이는 함수를 인수로 받는다.
 * - 인수로 제공된 함수는 각 요소에 적용된다.
 * - 적용한 결과가 새로운 요소로 매핑된다. (변환, 매핑)
 */
public class _03_Mapping {

    public static void main(String[] args) {
        //TODO 1. 스트림의 각 요소에 함수 적용하기
        List<Dish> orderedDishes = Menu.ORDERED_DISHES;
        List<String> names = orderedDishes.stream()
                .map(Dish::name)
                .collect(Collectors.toList());

        System.out.println("names = " + names);

        List<Integer> nameLengths = orderedDishes.stream()
                .map(Dish::name)
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println("nameLengths = " + nameLengths);


        //TODO 2. 스트림 평면화
        // 아래와 같이 하면, List<String[]>을 반환한다!
        List<String[]> nameToSeqBadCase = orderedDishes.stream()
                .map(Dish::name)
                .map(name -> name.split(""))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("nameToSeqBadCase = " + nameToSeqBadCase);

        // 중간에 Arrays.stream으로 String[]을 Stream<String>으로 바꿔보자
        List<Stream<String>> nameToSeqBadCase2 = orderedDishes.stream()
                .map(Dish::name)
                .map(name -> name.split(""))
                .map(strings -> Arrays.stream(strings))
                .distinct()
                .collect(Collectors.toList());  // 바꿔도 안된다. List<Stream<String>>가 반환된다.

        System.out.println("nameToSeqBadCase2 = " + nameToSeqBadCase2);

        //TODO 이는 flatMap으로 해결할 수 있다!
        // flatMap : 각 배열을 스트림이 아니라, 스트림의 콘텐츠로 매핑한다. -> ???
        // 즉, map과는 달리 flatMap은 하나의 평면화된 스트림을 반환한다.
        // 스트림의 각 값을 다른 스트림으로 만든 다음, 모든 스트림을 하나의 스트림으로 연결하는 기능을 수행한다.
        List<String> nameToSeqGoodCase = orderedDishes.stream()
                .map(Dish::name)
                .map(name -> name.split(""))
                .flatMap(strings -> Arrays.stream(strings))
                .distinct()
                .collect(Collectors.toList());

        System.out.println("nameToSeqGoodCase = " + nameToSeqGoodCase);
    }

}
