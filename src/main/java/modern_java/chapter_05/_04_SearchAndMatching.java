package modern_java.chapter_05;

import java.util.List;
import java.util.Optional;

/**
 * 특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리
 * - allMatch, anyMatch, noneMatch : 쇼트 서킷 기법, 즉 자바의 %%, ||와 같은 연산을 활용한다.
 * - findAny, findFirst, findAll : 마찬가지로 쇼트 서킷 기법이다. 결과를 찾으면 즉시 종료한다.
 * - findAny와 findFirst가 모두 있는 이유는 "병렬성" 때문이다. 병렬 실행에서는 첫 번째 요소를 찾기 어렵기 때문이다.
 *    때문에, 병렬 스트림에서는 제약이 적은 findAny를 사용한다.
 */
public class _04_SearchAndMatching {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 1. 적어도 한 요소와 일치하는지 확인
        boolean result1 = dishes.stream().anyMatch(Dish::isVegetarian);
        System.out.println("result1 = " + result1);


        //TODO 2. 모든 요소와 일치하는지 확인
        boolean result2 = dishes.stream().allMatch(dish -> dish.calories() < 1000);
        System.out.println("result2 = " + result2);


        //TODO 3. 모든 요소가 일치하지 않는지 확인
        boolean result3 = dishes.stream().noneMatch(dish -> dish.calories() >= 1000);
        System.out.println("result3 = " + result3);


        //TODO 4. 요소 검색
        Optional<Dish> findAny = dishes.stream().filter(Dish::isVegetarian).findAny();
        dishes.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);


        //TODO 5. 첫 번째 요소 찾기
        // 논리적인 아이템 순서가 정해져 있는 스트림인 경우, "첫 번째 요소"를 찾으려면?
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.stream()
                .map(number -> number * number)
                .filter(number -> number % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
