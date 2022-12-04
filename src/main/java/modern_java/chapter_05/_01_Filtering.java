package modern_java.chapter_05;

import java.util.List;
import java.util.stream.Collectors;
import modern_java.Dish;
import modern_java.Menu;

public class _01_Filtering {

    // Predicate<T>를 받는다.
    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;
        List<Dish> vegetarianMenu = dishes.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        List<Integer> numbers = List.of(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()  // 고유 요소 필터링
                .forEach(System.out::println);
    }

}
