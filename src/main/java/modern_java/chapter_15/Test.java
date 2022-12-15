package modern_java.chapter_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9),
                List.of(10, 11, 12)
        );

        List<Integer> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            result.addAll(list);
        }

        System.out.println(result);

        List<Integer> resultStream = lists.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println(resultStream);

        int[][] numbersArr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[] numbers = Arrays.stream(numbersArr)
                .flatMapToInt(arr -> Arrays.stream(arr))
                .toArray();

        System.out.println(Arrays.toString(numbers));
    }
}
