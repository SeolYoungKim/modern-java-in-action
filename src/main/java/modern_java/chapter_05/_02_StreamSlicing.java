package modern_java.chapter_05;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream의 요소를 선택하거나 스킵하는 다양한 방법
 * - Predicate 이용
 * - 스트림 처음 몇 개의 요소 무시
 * - 특정 크기로 스트림을 줄이는 방법 등
 */
public class _02_StreamSlicing {

    public static void main(String[] args) {
        //TODO 1. Predicate를 이용한 슬라이싱
        List<Dish> dishes = Menu.ORDERED_DISHES;  // 이미 칼로리 순서로 정렬된 요리 리스트
        List<Dish> filteredMenu = dishes.stream()
                .filter(dish -> dish.calories() < 320)  // filter를 이용하면 전부 순회함.
                .collect(Collectors.toList());

        //TODO 중간에 순회를 멈출 수 없을까??
        // 리스트가 정렬되어 있다면, takeWhile 이용!
        // 이를 이용하면, 320칼로리와 같거나 높은 음식이 나왔을 때, 반복 작업을 중단함.
        // 이는 무한 스트림을 포함한 모든 스트림에 Predicate를 적용해 슬라이스할 수 있다.
        // 이상, 이하에 대한 Predicate에 한하여 적용
        List<Dish> slicedMenu1 = dishes.stream()
                .takeWhile(dish -> dish.calories() < 320)  // 처음으로 false가 되는 지점에서 연산이 끝나고, 이후의 모든 요소는 버린다.
                .collect(Collectors.toList());

        System.out.println("slicedMenu1 = " + slicedMenu1);


        //TODO 반대로, 보다 큰 요소를 탐색할 때는 어떻게 해야할까?
        // dropWhile을 이용!
        // dropWhile은 Predicate가 처음으로 거짓이 되는 지점까지 발견된 요소를 버린다.
        // 이는 무한 스트림에서도 동작한다.
        // 이상, 이하에 대한 Predicate에 한하여 적용
        List<Dish> slicedMenu2 = dishes.stream()
                .dropWhile(dish -> dish.calories() < 320)  // 처음으로 false가 되는 지점에서 연산이 끝나지만, false가 되는 지점까지 발견된 모든 요소를 버린다.
                .collect(Collectors.toList());

        System.out.println("slicedMenu2 = " + slicedMenu2);


        //TODO 2. 스트림 축소
        // limit(long n)은 정렬되지 않은 스트림에도 사용될 수 있다. (물론, 결과도 정렬되어 있지는 않다)
        List<Dish> limitedDishes = dishes.stream()
                .filter(dish -> dish.calories() > 300)
                .limit(3)  // 요소를 3개 선택한 다음, 즉시 결과를 반환.
                .collect(Collectors.toList());

        System.out.println("limitedDishes = " + limitedDishes);

        //TODO 3. 요소 건너뛰기
        // skip(long n)은 처음 n개 요소를 제외한 스트림을 반환한다.
        // n개 이하의 요소를 포함하는 스트림에 skip(n)을 호출하면 빈 스트림이 반환된다.
        // limit(n)과 skip(n)은 상호 보완적인 연산을 수행한다.
        List<Dish> skippedDishes = dishes.stream()
                .filter(dish -> dish.calories() > 300)
                .skip(2)  // 요소를 3개 선택한 다음, 즉시 결과를 반환.
                .collect(Collectors.toList());

        System.out.println("skippedDishes = " + skippedDishes);
    }
}
