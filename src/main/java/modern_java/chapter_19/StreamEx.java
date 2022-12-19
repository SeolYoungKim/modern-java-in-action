package modern_java.chapter_19;

import java.util.stream.IntStream;

public class StreamEx {
    static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    /**
     * 아래 코드는 IllegalStateException을 발생시킨다.
     */
    static IntStream primes(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(
                IntStream.of(head),
                primes(tail(numbers).filter(n -> n % head != 0))  // 재귀적 정의 허용 X
        );
    }

    public static void main(String[] args) {
        IntStream numbers = numbers();
        int head = head(numbers);
        IntStream filtered = tail(numbers).filter(n -> n % head != 0);
    }
}
