package modern_java.chapter_06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import modern_java.Dish;
import modern_java.Menu;

public class _05_Example {

    public static void main(String[] args) {
        //TODO 스트림에 적용 가능한 Collector가 되었다!
        List<Dish> dishes = Menu.DISHES;
        List<Dish> dishes1 = dishes.stream().collect(new ToListCollector<>());
        System.out.println("dishes1 = " + dishes1);


        //TODO 컬렉터 구현을 만들지 않고도 커스텀 수집 수행하기
        // - IDENTITY_FINISH 수집 연산에서는 Collector 인터페이스를 완전히 새로 구현하지 않고도 같은 결과를 얻을 수 있음
        // - Stream은 세 함수(발행, 누적, 합침)를 인수로 받는 collect 메서드를 오버로드하며, 각 메서드는 Collector 인터페이스의 메서드가 반환하는 함수와 같은 기능을 수행함.
        // - 하지만 이는 가독성이 떨어지므로, 구현해서 사용하는 편이 낫다.
        // - 또한, characteristics를 전달할 수 없다. (IDENTITY_FINISH, CONCURRENT지만 UNORDERED는 아닌 컬렉터가 되어버림)
        ArrayList<Object> wow = dishes.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll);


        //TODO nonMatch는 스트림이 비어있으면 true를 반환한다!!!!!!
        //https://www.geeksforgeeks.org/stream-nonematch-method-java-examples/
        boolean b = IntStream.rangeClosed(2, 1)  // 빈 스트림이 넘어간다.
                .noneMatch(i -> {  //TODO 스트림에서 넘어온게 없으면 true인듯하다????
                    System.out.println("이게 실행이 된다고?");
                    return 2 % i == 0;
                });
        System.out.println("b = " + b);
    }
}
