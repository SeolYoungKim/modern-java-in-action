package modern_java;

import Dish.Type;
import java.util.List;
import modern_java.Dish.Type;

public class Menu {

    public static final List<Dish> DISHES = List.of(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    public static final List<Dish> ORDERED_DISHES = List.of(
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("pork", false, 800, Type.MEAT)
    );
}
