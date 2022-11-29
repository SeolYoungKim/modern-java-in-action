package chapter_03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _05_Example {

    private int b = 2;

    private Stream<Integer> calculate(Stream<Integer> stream, Integer a) {
//        a = 10;  // 변경 불가
        return stream.map(t -> t * a + b);  // 내부에서 외부의 a, b를 참조하고 있음. a, b는 컴파일러가 final로 간주.
    }

    public static void main(String[] args) {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);  // portNumber 변수를 캡처  ->  람다 캡처링을 한다 -> 새로운 스택이 생성된다 -> 쓰레드가 생성된다?
//        portNumber = 31337;  // portNumber가 값이 변하기 때문에 람다에서 캡처링할 수 없다.

        // 클로저
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        _05_Example example = new _05_Example();
        List<Integer> result = example.calculate(list.stream(), 3)
                .collect(Collectors.toList());

        System.out.println(result);

        // Lambda.
//        (server) -> server.isRunning();  // 람다 파라미터 참조

        // Closure. 외부의 server 라는 변수를 참조
//        () -> server.isRunning();  // 외부 변수 참조
    }

}
