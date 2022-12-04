# Collector 인터페이스
```java
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    BinaryOperator<A> combiner();
    Function<A, R> finisher();
    Set<Characteristics> characteristics();
}
```
- `T` : 수집될 스트림 항목의 제네릭 타입
- `A` : 누적자(accumulator), 수집 과정에서 중간 결과를 누적하는 객체의 형식
- `R` : 수집 연산 결과 객체의 형식(대개 컬렉션. 항상 그런건 아님)


- 누적 과정에서 사용되는 객체가 수집 과정의 최종 결과로 사용된다.


## Collector 인터페이스의 메서드
- supplier ~ finisher 네개의 메서드는 collect 메서드에서 실행하는 함수를 반환한다.
- characteristics는 collect 메서드가 어떤 최적화(병렬화와 같은)를 이용해서 리듀싱 연산을 수행할 것인지 결정하도록 돕는 힌트 특성 집합을 제공한다.
- 메서드 설명 : modern_java.chapter_06.ToListCollector 참고


1. 스트림을 두 개의 서브파트로 분할
2. 서브파트의 크기가 충분히 작아지도록 스트림 분할 반복
   - 스트림을 분할해야 하는지 정의하는 조건이 거짓으로 바뀌기 전 까지 원래 스트림을 재귀적으로 분할
   - 분산된 작업의 크기가 너무 작을 경우, 병렬 수행 속도는 순차 수행 속도보다 느려진다. (병렬 수행 효과 상쇄)
   - 일반적으로 프로세싱 코어의 개수를 초과하는 병렬 작업은 비효율적임 
3. 순차 알고리즘으로 각각의 서브스트림을 병렬로 처리
   - 모든 서브스트림의 각 요소에 리듀싱 연산을 순차적으로 적용 -> 병렬 처리
4. 각 서브 스트림을 독립적으로 처리한 결과 합치기
   - combiner 메서드가 반환하는 함수로 모든 부분 결과를 쌍으로 합침
   - 분할된 서브스트림의 결과를 합치면서 연산 완료