package modern_java.chapter_05;

import java.util.List;

public class _05_Quiz {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO map과 reduce를 연결하는 기법을 map-reduce 패턴이라고 하며, 쉽게 병렬화 하는 특징을 가졌다.
        // 이는 구글이 웹 검색에 적용하면서 유명해졌다.
        Integer result1 = dishes.stream()
                .map(dish -> 1)
                .reduce(0, Integer::sum);

        System.out.println("result1 = " + result1);
    }

}
