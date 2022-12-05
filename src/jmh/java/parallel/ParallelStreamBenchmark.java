package parallel;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@BenchmarkMode(Mode.AverageTime)  // 벤치마크 대상 메서드를 실행하는 데 걸린 평균 시간 측정
@OutputTimeUnit(TimeUnit.MILLISECONDS)  // 벤치마크 결과를 밀리초 단위로 출력
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})  // 4Gb의 힙 공간을 제공한 환경에서, 두번 벤치마크를 수행해 결과의 신뢰성 확보
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

    @Benchmark
    public long sequentialSum() {
        return LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }

    @Benchmark
    public long parallelSum() {
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    @State(Scope.Benchmark)
    public static class MyState {

        @TearDown(Level.Invocation)
        public void tearDown() {
            System.gc();
        }
    }


    // 책에서는 아래의 예시 그대로 사용하였으나, 이는 @State 내에서 사용할 수 있다고 한다.
//    @TearDown(Level.Invocation)  // 매번 벤치마크 실행한 다음에는 가비지 컬렉터 동작 시도
//    public void tearDown() {
//        System.gc();
//    }
}
