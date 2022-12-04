package modern_java.chapter_05;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import modern_java.Dish;
import modern_java.Menu;

/**
 * Stream<T>는 sum 메서드가 없다.
 * 숫자 스트림을 효율적으로 처리할 수 있도록 기본형 특화 스트림 제공
 * - boxing 비용을 피할 수 있도록 InteStream, DoubleStream, LongStream 제공
 * - sum, max와 같은 숫자 관련 리듀싱 연산 제공
 * - 필요 시, 객체 스트림으로 복원하는 기능 제공
 * - 박싱 과정에서 일어나는 효율성과 관련이 있으며, 스트림에 추가 기능을 제공하지는 않음!
 */
public class _07_NumberStream {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 1. 숫자 스트림으로 매핑하기
        int calories = dishes.stream()
                .mapToInt(Dish::calories)
                .sum();  // 스트림이 비어있으면 0 반환. max, min, average 제공


        //TODO 2. 객체 스트림으로 복원하기
        IntStream intStream = dishes.stream().mapToInt(Dish::calories);
        Stream<Integer> boxed = intStream.boxed();  // IntStream To Stream<Integer>


        //TODO 3. OptionalInt
        OptionalInt maxCalories = dishes.stream()
                .mapToInt(Dish::calories)
                .max();

        int max = maxCalories.orElse(0);


        //TODO 4. 숫자 범위
        long oddNumberClosed = IntStream.rangeClosed(1, 100)  // 첫번째 값, 마지막 값 모두 포함
                .filter(n -> n % 2 != 0)
                .count();

        long oddNumber = IntStream.range(1, 100)  // 첫번째 값 포함, 마지막 값 미포함
                .filter(n -> n % 2 != 0)
                .count();

        System.out.println("rangeClosed : " + oddNumberClosed + ", range : " + oddNumber);


        //TODO 피타고라스 수
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100) // 내부에서 Stream을 썼을 땐 flatMap을 쓰자.
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        //TODO 최적화
        // 만족하는 세 수를 만들고, 우리가 원하는 조건에 맞는 결과만 필터링 하는 것이 더 최적화된 방법이다. (제곱근 두번 계산 안하므로)
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100) // 내부에서 Stream을 썼을 땐 flatMap을 쓰자.
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                )
                .limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

}
