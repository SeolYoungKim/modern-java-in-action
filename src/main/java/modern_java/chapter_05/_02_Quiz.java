package modern_java.chapter_05;

import java.util.List;
import java.util.stream.Collectors;
import modern_java.Dish;
import modern_java.Dish.Type;
import modern_java.Menu;

public class _02_Quiz {

    public static void main(String[] args) {
        List<Dish> orderedDishes = Menu.ORDERED_DISHES;
        System.out.println(orderedDishes);

        // 처음 등장하는 두 고기 요리 필터링
        List<Dish> limitedDishes = orderedDishes.stream()
                .filter(dish -> dish.type() == Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(limitedDishes);

        List<Dish> skippedDishes = orderedDishes.stream()
                .filter(dish -> dish.type() == Type.MEAT)
                .skip(2)
                .collect(Collectors.toList());

        System.out.println(skippedDishes);
    }

}
