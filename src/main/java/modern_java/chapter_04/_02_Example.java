package modern_java.chapter_04;

import java.util.List;
import java.util.stream.Collectors;
import modern_java.Dish;
import modern_java.Menu;

public class _02_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;  // data source

        List<String> highCaloricDishNames = dishes.stream()
                .filter(dish -> dish.calories() > 300)  // 데이터 처리 연산
                .map(Dish::name)
                .limit(3)  // 스트림 크기 축소. (요소 갯수 제한)
                .collect(Collectors.toList());  // collect가 호출되기 전 까지 메서드 호출이 저장된다.

        System.out.println(highCaloricDishNames);
    }

}
