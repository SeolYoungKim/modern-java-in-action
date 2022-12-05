package modern_java.chapter_07;

public class _01_SumHarness {

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        long init = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long result = ForkJoinSumCalculator.forkJoinSum(10_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
            System.out.println("Result: " + result);
//            System.out.println("Fastest execution done in " + fastest + " msecs");
        }

        long end = (System.nanoTime() - init) / 1_000_000;
        System.out.println("SideEffect parallel sum done in: " + end);
    }
}
