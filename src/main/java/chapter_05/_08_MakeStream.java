package chapter_05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 일련의 값, 배열, 파일, 함수, 무한스트림...
 * 무한 스트림은 정렬하거나, 리듀스 할 수 없다!
 */
public class _08_MakeStream {

    public static final String FILE_PATH = "src/main/java/chapter_05/data.txt";

    public static void main(String[] args) throws IOException {
        //TODO 01. 값으로 스트림 만들기
        Stream.of("Modern ", "Java ", "In ", "Action")
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<Object> emptyStream = Stream.empty();


        //TODO 02. null이 될 수 있는 객체로 스트림 만들기
        // 때로는 null이 될 수 있는 객체를 스트림으로 만들어야 할 때가 있음.
        String homeValue = System.getProperty("home");  // 제공된 키에 대응하는 속성이 없으면 null

        // 기존 방식
        Stream<?> before = homeValue == null ? Stream.empty() : Stream.of(homeValue);

        // 자바 9부터 지원되는 방식
        Stream<String> after = Stream.ofNullable(homeValue);

        // 유용하게 사용하는 법
        Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)))
                .forEach(System.out::println);


        //TODO 03. 배열로 스트림 만들기
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();


        //TODO 04. 파일로 스트림 만들기
        // 파일을 처리하는 등의 I/O 연산에 사용하는 자바의 NIO API(Non-blocking I/O)도 스트림 API를 활용할 수 있다.
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH), Charset.defaultCharset())) {
            long words = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();

            System.out.println(words);
        }


        //TODO 05. 무한 스트림 만들기 (언바운드 스트림)
        // Stream.iterate & Stream.generate
        // 크기가 고정되지 않은 스트림을 만들 수 있다.
        // 해당 스트림은 요청할 때 마다 주어진 함수를 이용해서 값을 만든다.
        // limit(n)과 함께 사용한다.

        Stream.iterate(0, n -> n + 2)
                .limit(3)
                .forEach(System.out::println);

        //TODO 피보나치
        // iterate는 요소를 생산할 때 적용할 람다가 필요하다.
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(5)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        String fibonacci = Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(5)
                .map(t -> String.valueOf(t[0]))
                .collect(Collectors.joining(" "));

        System.out.println("fibonacci = " + fibonacci);


        //TODO Predicate를 이용하는 방법
        Stream.iterate(0, n -> n < 10, n -> n + 4)
                .forEach(System.out::println);

        // filter는 연산이 언제 끝나는지 알 수 없다. 모든 stream 요소를 검사하기 때문이다. (쇼트 서킷 적용 X)
//        Stream.iterate(0, n -> n + 4)
//                .filter(n -> n < 10)
//                .forEach(System.out::println);

        Stream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 10)  // 해당 조건 까지만 계산하고, 나머지는 안함.
                .forEach(System.out::println);



        //TODO 06. generate 메서드
        // iterate와 비슷하게 무한 스트림을 생성함.
        // 생산된 각 값을 "계산하지 않는다!!"
        Stream.generate(Math::random)  // 상태가 없는 메서드. 즉, 나중에 계산에 사용할 값을 저장하지 않음!!
                .limit(5)
                .forEach(System.out::println);

        IntStream generate = IntStream.generate(() -> 1).limit(5);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {  // 익명 클래스는 이걸 커스터마이징 할 수 있다.
                return 2;  // 바로 부작용이 생길 수 있다는 예제이다..! 상태를 바꾸는건 위험하다.
            }
        });

        //TODO 람다는 상태를 바꾸지 않는다!

        //TODO generate로 피보나치를 구현하면 어떻게 될까?
        // 아래의 객체는 기존 피보나치 요소와 두 인스턴스 변수에 어떤 피보나치 요소가 들어있는지 추적 -> 가변 상태
        // 객체 상태가 바뀌며 새로운 값을 생산.
        // 반면에, iterate는 각 과정에서 새로운 값을 생성하면서도, 기존 상태를 바꾸지 않는 순수한 불변 상태를 유지함.
        // 스트림을 병렬로 처리하면서, 올바른 결과를 얻으려면 불변 상태 기법을 고수해야 함.
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrev = this.previous;
                int nextVal = this.previous + this.current;
                this.previous = this.current;
                this.current = nextVal;

                return oldPrev;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }

}
