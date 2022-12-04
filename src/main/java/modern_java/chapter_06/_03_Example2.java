package modern_java.chapter_06;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import modern_java.CaloricLevel;
import modern_java.Dish;
import modern_java.Dish.Type;
import modern_java.Menu;

public class _03_Example2 {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 4. 다수준 그룹화
        // - N수준 트리 구조로 표현되는 N수준 맵이 된다.
        Map<Type, Map<CaloricLevel, List<Dish>>> multiGroup = dishes.stream().collect(
                groupingBy(Dish::type,  // 첫 번째 수준의 분류 함수 -> 각 키의 버킷을 만든다.
                        groupingBy(dish -> {  // 두 번째 수준의 분류 함수 -> 각 버킷을 서브스트림 컬렉터로 채워간다.
                            if (dish.calories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.calories() <= 700) {
                                return CaloricLevel.NORMAL;
                            }

                            return CaloricLevel.FAT;
                        })
                )
        );

        System.out.println("multiGroup = " + multiGroup);


        //TODO 5. 서브 그룹으로 데이터 수집
        // - 분류 함수 한개를 갖는 gruopingBy(f)는 사실, groupingBy(f, toList())의 축약형이다.
        Map<Type, Long> typesCount = dishes.stream().collect(groupingBy(Dish::type, counting()));
        System.out.println("typesCount = " + typesCount);

        Map<Type, Optional<Dish>> maxCaloricByType = dishes.stream()
                .collect(groupingBy(Dish::type, maxBy(comparingInt(Dish::calories))));
        System.out.println("maxCaloricByType = " + maxCaloricByType);


        //TODO 6. 컬렉터 결과를 다른 형식에 적용
        Map<Type, Dish> mostCaloricByType = dishes.stream()
                .collect(groupingBy(Dish::type,  // 분류 함수를 이용하여 원래 스트림을 분할함.
                        collectingAndThen(  // 두 번째 컬렉터로 각 서브스트림을 독립적으로 처리함. (Collector가 반환한 결과를 다른 형식으로 활용할 수 있게 해줌.)
                                maxBy(comparingInt(Dish::calories)),  // 리듀싱 컬렉터. 조건에 맞는 요소를 Optional로 감싸서 반환.
                                Optional::get)));  // collectionAndThen은 Optional에서 값을 추출해서 반환.
        //TODO 결과적으로 두 번째 수준 컬렉터의 결과는 grouping 맵의 value가 된다.

//        Map<Type, Dish> mostCaloricByType = dishes.stream()
//                .collect(Collectors.toMap(
//                        Dish::type,
//                        Function.identity(),
//                        BinaryOperator.maxBy(comparingInt(Dish::calories)))
//                );

        System.out.println("mostCaloricByType = " + mostCaloricByType);


        //TODO groupingBy와 함께 사용하는 다른 컬렉터 예제
        // - 같은 그룹으로 분류된 모든 요소에 리듀싱 작업을 수행하려면?
        // - groupingBy에 두 번째 인수로 컬렉터 전달
        Map<Type, Integer> totalCaloriesByType = dishes.stream()
                .collect(groupingBy(Dish::type, summingInt(Dish::calories)));
        System.out.println("totalCaloriesByType = " + totalCaloriesByType);

        Map<Type, Set<CaloricLevel>> caloric = dishes.stream()
                .collect(groupingBy(Dish::type, mapping(dish -> {
                            if (dish.calories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.calories() <= 700) {
                                return CaloricLevel.NORMAL;
                            }
                            return CaloricLevel.FAT;
                        },
                        toCollection(HashSet::new)))); //TODO toCollection을 이용해 구체적인 구현체를 정해줄 수 있다.

        System.out.println("caloric = " + caloric);



    }

    private static final Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", List.of("greasy", "salty"));
        dishTags.put("beef", List.of("salty", "roasted"));
        dishTags.put("chicken", List.of("fried", "crisp"));
        dishTags.put("french fries", List.of("greasy", "fried"));
        dishTags.put("rice", List.of("light", "natural"));
        dishTags.put("season fruit", List.of("fresh", "natural"));
        dishTags.put("pizza", List.of("tasty", "salty"));
        dishTags.put("prawns", List.of("tasty", "roasted"));
        dishTags.put("salmon", List.of("delicious", "fresh"));
    }
}
