package chapter_05;

import java.util.List;

/**
 * 리듀싱 연산?
 * 스트림 요소를 조합해서 더 복잡한 질의를 표현하는 방법 (모든 칼로리의 합계, 메뉴에서 가장 칼로리가 높은 요리 찾기...)
 * 이러한 질의를 수행하기 위해서는, Integer같은 결과가 나올 때 까지 스트림의 모든 요소를 반복 처리해야 함
 * - 초깃값과 BinaryOperator 사용
 * - 초깃값 & 스트림 요소 -> 람다식 호출 및 연산 -> 누적값 & 다음 스트림의 요소 -> 람다식 호출 및 연산  ... 모든 스트림 요소가 소비될 때 까지 수행
 */
public class _05_Reducing {

    public static void main(String[] args) {
        //TODO 1. 요소의 합
        // reduce를 이용하여 반복 패턴을 추상화 할 수 있다.
        // reduce는 두 개의 인수를 갖는다. (초깃값과 BinaryOperator<T>)
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Integer result1 = numbers.stream()
                .reduce(0, Integer::sum);  // 초깃값 0

        System.out.println("result1 = " + result1);

        Integer result2 = numbers.stream()
                .reduce(1, (a, b) -> a * b);  // 초깃값 1

        System.out.println("result2 = " + result2);

        // 초깃값을 설정하지 않을 수 있으나, 이런 경우 Optional 반환
        numbers.stream()
                .reduce((a, b) -> a * b)
                .ifPresent(System.out::println);


        //TODO 2. 최댓값과 최솟값
        numbers.stream()
                .reduce(Integer::max)  // (x, y) -> x > y ? x : y 와 동일
                .ifPresent(integer -> System.out.println("result3 = " + integer));

        numbers.stream()
                .reduce(Integer::min)  // (x, y) -> x < y ? x : y 와 동일
                .ifPresent(integer -> System.out.println("result4 = " + integer));
    }
}
