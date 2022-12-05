package modern_java.chapter_07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 병렬 스트림
 */
public class _01_Example {

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .sequential()  // 순차 실행.
                .reduce(0L, Long::sum);
    }

    public static long sequentialSumNotBoxing(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
//                .sequential() // 섞어 쓸 수 있다. 하지만 마지막에 호출된 메서드가 전체 파이프라인에 영향을 미친다.(이 경우 병렬!)
                .limit(n)
                .parallel()  // 스트림을 병렬 스트림으로 변환 (스트림이 여러 청크로 분할 돼 있음)
                .reduce(0L, Long::sum);
    }

    public static long parallelSumNotBoxing(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }


    /**
     * 아래 코드는 본질적으로 순차 실행할 수 있도록 구현되어있다.
     * - 병렬로 실행하면 대참사 발생
     * - 특히, total에 접근할 때 마다 (다수에 스레드에서 동시에 데이터 접근) 데이터 레이스 문제가 발생한다. (경쟁)
     * - 동기화로 문제를 해결하다 보면, 병렬화라는 특성이 사라질 것이다..
     */
    public static long sideEffectSum(long n) {
        Accmulator accmulator = new Accmulator();
        LongStream.rangeClosed(1, n).forEach(accmulator::add);
        return accmulator.total;
    }

    /**
     * 성능은 둘째 치고, 올바른 결과값이 나오지 않으며, 심지어 매번 실행 시 마다 결과값이 달라진다.
     * - 여러 쓰레드에서 동시에 누적자에 접근하여 total += value를 실행하기 때문이다.
     * - 병렬 스트림에서는 "상태 공유"에 대한 부작용을 피해야 한다.
     */
    public static long sideEffectParallelSum(long n) {
        Accmulator accmulator = new Accmulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accmulator::add);
        return accmulator.total;
    }

    public static class Accmulator {

        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
