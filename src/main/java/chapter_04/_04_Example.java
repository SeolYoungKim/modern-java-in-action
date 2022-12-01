package chapter_04;

import java.util.List;
import java.util.stream.Collectors;

public class _04_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;  // data source

        List<String> highCaloricDishNames = dishes.stream()
                .filter(dish -> {
                    System.out.println("filtering : " + dish.name());
                    return dish.calories() > 300;
                })  // 데이터 처리 연산
                .map(dish -> {
                    System.out.println("mapping : " + dish.name());
                    return dish.name();
                })
                .limit(3)  // 스트림 크기 축소. (요소 갯수 제한)
                .collect(Collectors.toList());  // collect가 호출되기 전 까지 메서드 호출이 저장된다.

        System.out.println(highCaloricDishNames);
    }

}
