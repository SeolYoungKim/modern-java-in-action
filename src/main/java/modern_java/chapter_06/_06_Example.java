package modern_java.chapter_06;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class _06_Example {

    // 대상 숫자의 제곱근보다 작은 소수만 사용하도록 해보자.
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);  // 제곱근 사용
        // 다음 소수가 대상의 루트보다 크면 소수로 나누는 검사를 멈춘다.
        // filter는 전체 스트림을 처리해야 하기 때문에 takeWhile을 쓰자.
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidateRoot % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    // 직접 takeWhile 메서드 구현하기
    public static <T> List<T> takeWhile(List<T> list, Predicate<T> p) {
        int i = 0;
        for (T t : list) {
            if (!p.test(t)) {  // 현재 항목이 predicate 만족하는지 확인
                return list.subList(0, i);  // 만족하지 않으면 현재까지 검사한 항목의 이전 항목 하위 리스트 반환
            }
            i++;
        }

        return list;
    }
}
