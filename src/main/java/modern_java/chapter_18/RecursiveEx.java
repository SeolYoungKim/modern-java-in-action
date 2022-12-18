package modern_java.chapter_18;

import java.util.stream.LongStream;

public class RecursiveEx {
    public static void main(String[] args) {

    }

    static long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
    }

    /**
     * 단일 스택 프레임을 재사용하는 팩토리얼의 꼬리 재귀 정의
     */
    static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

    /**
     * 여러 스택 프레임을 사용하는 팩토리얼의 재귀 정의
     */
    static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }
}
