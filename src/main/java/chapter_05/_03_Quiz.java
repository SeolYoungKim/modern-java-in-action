package chapter_05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _03_Quiz {

    public static void main(String[] args) {
        // 1. 숫자 -> 제곱근으로 반환
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> results1 = numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());

        System.out.println("results1 = " + results1);

        // 2. 두 개의 숫자 리스트 -> 모든 숫자 쌍의 리스트로 변환
        List<Integer> left = List.of(1, 2, 3);
        List<Integer> right = List.of(3, 4);
        List<List<Integer>> results2 = left.stream()
                .flatMap(l -> right.stream().map(r -> List.of(l, r)))
                .collect(Collectors.toList());

        System.out.println("results2 = " + results2);


        // 3. 합이 3으로 나누어떨어지는 쌍만 반환 - 내 풀이
        List<int[]> results3 = left.stream()
                .flatMap(l -> right.stream().map(r -> new int[]{l, r}))
                .filter(arr -> Arrays.stream(arr).sum() % 3 == 0)
                .collect(Collectors.toList());

        for (int[] ints : results3) {
            System.out.println("results3 = " + Arrays.toString(ints));
        }


        // 4. 책의 더 깔끔한 풀이
        List<List<Integer>> results3OfBook = left.stream()
                .flatMap(l -> right.stream()  // TODO 착안점: 외부의 스트림 요소를 내부 스트림에서 사용할 수 있다!!!
                        .filter(r -> (l + r) % 3 == 0)
                        .map(r -> List.of(l, r)))
                .collect(Collectors.toList());

        System.out.println("results3OfBook = " + results3OfBook);
    }

}
