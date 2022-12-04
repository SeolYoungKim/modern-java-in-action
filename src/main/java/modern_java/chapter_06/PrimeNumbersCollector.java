package modern_java.chapter_06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector
        implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>(){{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());  // 두개의 빈 리스트를 포함하는 맵으로 수집 동작 시작
        }};
    }

    // 스트림의 요소를 어떻게 수집할 것인가?
    // 언제든지 원할 때 수집 과정의 중간 결과, 즉 지금까지 발견한 소수를 포함하는 누적자에 접근할 수 있도록 해야 함
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> {
            acc.get(_06_Example.isPrime(acc.get(true),  // 지금까지 발견한 소수 리스트를 isPrime으로 전달
                            candidate))  // isPrime 결과에 따라 소수 리스트와 비소수 리스트를 만든다.
                    .add(candidate);  // candidate를 알맞은 리스트에 추가한다.
        };
    }

    // 참고로 알고리즘 자체가 순차적이기 때문에, 병렬 컬렉터를 실제 병렬로 사용할 순 없다.
    // 학습 목적으로 구현하였으나, 실제로 사용할 일이 없다면 UnsupportedOperationException을 던지도록 하자.
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
