package modern_java.chapter_15;

import java.util.Arrays;

public class _01_Example {
    static void singleCore() {
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
    }

    // 진짜 멀티코어는 아니고.. 이런 식으로 동시에 수행한다는 뜻이다.
    static void multiCore() {
        long sum0 = 0;
        for (int i = 0; i < 250_000; i++) {
            sum0 += i;
        }

        for (int i = 250_000; i < 500_000; i++) {
            sum0 += i;
        }

        for (int i = 500_000; i < 750_000; i++) {
            sum0 += i;
        }

        for (int i = 750_000; i < 1_000_000; i++) {
            sum0 += i;
        }

        // 쓰레드가 네 개가 생성되고, start()로 실행 후, join()으로 완료될 때까지 기다렸다가 합산
    }

    public static void main(String[] args) {
        // 명시적 루프 대신 아래와 같이 할 수 있다.
        int[] numbers = {1, 23, 4, 4, 5, 6, 436};
        int sum = Arrays.stream(numbers).parallel().sum();
    }
}
