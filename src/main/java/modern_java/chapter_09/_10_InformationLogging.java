package modern_java.chapter_09;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

public class _10_InformationLogging {
    public static void main(String[] args) {
        List<Integer> result = IntStream.rangeClosed(1, 10)
                .peek(i -> System.out.println("from stream: " + i))
                .mapToObj(i -> i + 17)
                .peek(i -> System.out.println("after map: " + i))
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.println("after filter: " + i))
                .limit(3)
                .peek(i -> System.out.println("after limit: " + i))
                .collect(toList());

    }
}
