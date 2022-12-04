package modern_java.chapter_06;

import static java.util.stream.Collectors.counting;

import modern_java.Dish;
import modern_java.Menu;
import java.util.Comparator;
import java.util.List;

public class _02_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 1.count
        Long counting = dishes.stream().collect(counting());  // 이는 다른 컬렉터와 함께 사용될 때 위력을 발휘함.
        long count = dishes.stream().count();


        //TODO 2. 최댓값, 최솟값
        Comparator<Dish>

    }

}
