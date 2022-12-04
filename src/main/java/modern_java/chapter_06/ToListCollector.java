package modern_java.chapter_06;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 새로운 결과 컨테이너 만들기
     * - 빈 결과로 이루어진 Supplier를 반환해야 함
     * - 수집 과정에서 빈 누적자 인스턴스를 만드는 파라미터가 없는 함수임
     * - 빈 리스트를 반환하자
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;  // 수집 연산의 시작점
    }

    /**
     * 결과 컨테이너에 요소 추가하기
     * - 리듀싱 연산을 수행하는 함수를 반환함
     * - 스트림에서 n번째 요소를 탐색할 때 두 인수, 즉 스트림의 n-1개 항목을 수집한 누적자와 n번째 요소를 함수에 적용함.
     * - 함수의 반환값은 void로, 요소를 탐색하면서 적용하는 함수에 의해 누적자 내부 상태가 바뀌므로, 누적자가 어떤 값인지 단정 불가.
     * - 아래의 메서드는 "이미 탐색한 항목을 포함하는 리스트"에 "현재 항목을 추가"하는 연산을 수행하도록 구성했다.
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {  // Biconsumer에는 accumulator와 stream의 요소가 들어간다.
        return List::add;  // 탐색한 항목을 누적하고, 누적자를 고침
    }

    /**
     * 두 결과 컨테이너 병합
     * - 마지막으로 리듀싱 연산에서 사용할 함수를 반환하는 메서드
     * - 스트림의 서로 다른 서브파트를 병렬로 처리할 때, 누적자가 이 결과를 어떻게 처리할지 정의
     * - 스트림의 리듀싱을 병렬로 수행할 수 있다
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {  // 두 번째 콘텐츠와 합쳐서 첫 번째 누적자를 고침
            list1.addAll(list2);
            return list1;  // 변경된 첫 번째 누적자 반환
        };
    }

    /**
     * 최종 변환 값을 결과 컨테이너로 적용하기
     * - 스트림 탐색을 끝내고 누적자 객체를 최종 결과로 변환
     * - 때문에, 누적자 과정을 끝낼 때 호출할 함수를 반환해야 함
     * - 때로는 누적자 객체가 이미 최종 결과인 상황도 있음. 이런 때는 변환 과정이 필요 없기 때문에, 항등 함수를 반환함.
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();  // 항등 함수
    }

    /**
     * 컬렉터의 연산을 정의하는 Characteristics 형식의 불변 집합을 반환
     * - 아래의 힌트를 제공
     *     - 스트림을 병렬로 리듀스 할 것인지
     *     - 병렬로 리듀스 한다면 어떤 최적화를 선택해야 할지
     * - 다음 세 항목을 포함하는 열거형
     *     - UNORDERED : 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않음.
     *     - CONCURRENT : 다중 스레드에서 accumulator 함수를 동시에 호출할 수 있음. (이 컬렉터는 병렬 리듀싱을 수행)
     *                    컬렉터 플래그에 UNORDERED를 함께 설정하지 않은 경우, 데이터 소스가 정렬되어 있지 않은(순서가 무의미한) 상황에서만
     *                    병렬 리듀싱을 수행할 수 있음
     *     - IDENTITY_FINISH : finisher 메서드가 반환하는 함수는 단순히 identify를 적용할 뿐이므로, 이를 생략할 수 있음
     *                         따라서, 리듀싱 과정의 최종 결과로 누적자 객체를 바로 사용할 수 있음
     *                         누적자 A를 결과 R로 안전하게 형변환 할 수 있음
     */
    @Override
    public Set<Characteristics> characteristics() {
        // 해당 클래스에서는 finisher에 대한 추가 변환이 필요 없다. -> IDENTITY_FINISH
        // 순서는 상관이 없다. -> UNORDERED
        // 요소의 순서가 무의미 하다. -> CONCURRENT
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT, UNORDERED));
    }

    public static void main(String[] args) {
        //TODO 대략적인 적용 로직
        ToListCollector<String> toListCollector = new ToListCollector<>();
        String element = "element";

        //TODO 1. accumulator를 만든다.
        List<String> accumulator = toListCollector.supplier().get();

        //TODO 2. 스트림의 요소를 저장한다.
        toListCollector.accumulator().accept(accumulator, element);

        //TODO 3. 스트림에 요소가 남아있다면 다음 요소에 대해 2번 과정을 계속한다.

        //TODO 4. 스트림에 요소가 없다면 finisher를 호출하여 결과를 반환한다.
        List<String> result = toListCollector.finisher().apply(accumulator);  // 여기서는 accumulator를 결과로 그대로 사용한다.


        //TODO combiner를 사용하는 로직
        // 1. 스트림을 두 개의 서브파트로 분할
        // 2. 서브파트의 크기가 충분히 작아지도록 스트림 분할 반복
        //    - 스트림을 분할해야 하는지 정의하는 조건이 거짓으로 바뀌기 전 까지 원래 스트림을 재귀적으로 분할
        //    - 분산된 작업의 크기가 너무 작을 경우, 병렬 수행 속도는 순차 수행 속도보다 느려진다. (병렬 수행 효과 상쇄)
        //    - 일반적으로 프로세싱 코어의 개수를 초과하는 병렬 작업은 비효율적임
        // 3. 순차 알고리즘으로 각각의 서브스트림을 병렬로 처리
        //    - 모든 서브스트림의 각 요소에 리듀싱 연산을 순차적으로 적용 -> 병렬 처리
        // 4. 각 서브 스트림을 독립적으로 처리한 결과 합치기
        //    - combiner 메서드가 반환하는 함수로 모든 부분 결과를 쌍으로 합침
        //    - 분할된 서브스트림의 결과를 합치면서 연산 완료
        List<String> acc1 = List.of("acc1");
        List<String> acc2 = List.of("acc2");
        List<String> result1 = toListCollector.combiner().apply(acc1, acc2);

        List<String> acc3 = List.of("acc3");
        List<String> acc4 = List.of("acc4");
        List<String> result2 = toListCollector.combiner().apply(acc3, acc4);

        List<String> finalAccumulator = toListCollector.combiner().apply(result1, result2);

        //TODO 5. finisher로 마무리!
        List<String> finalReasult = toListCollector.finisher().apply(finalAccumulator);
    }
}
