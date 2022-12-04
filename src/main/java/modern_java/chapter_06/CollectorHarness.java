package modern_java.chapter_06;

import static modern_java.chapter_06._06_Example.partitionPrimes;

// JMH 같은 것을 사용해도 됨.
public class CollectorHarness {

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }

            System.out.println("Fastest execution done in " + fastest + " msecs");
        }
    }

}
