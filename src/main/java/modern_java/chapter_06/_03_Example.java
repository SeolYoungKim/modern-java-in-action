package modern_java.chapter_06;

import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modern_java.CaloricLevel;
import modern_java.Dish;
import modern_java.Dish.Type;
import modern_java.Menu;

public class _03_Example {

    public static void main(String[] args) {
        List<Dish> dishes = Menu.DISHES;

        //TODO 1. 타입에 따른 Dish 그룹화
        // Dish::type -> 분류 함수. 이 기준으로 분류하여 List<Dish>를 모은다.
        Map<Type, List<Dish>> dishesByType = dishes.stream().collect(groupingBy(Dish::type));
        System.out.println("dishesByType = " + dishesByType);

        //TODO 2. 복잡한 분류
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = dishes.stream().collect(groupingBy(dish -> {
            if (dish.calories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.calories() <= 700) {
                return CaloricLevel.NORMAL;
            }

            return CaloricLevel.FAT;
        }));
        System.out.println("dishesByCaloricLevel = " + dishesByCaloricLevel);

        //TODO 3. 그룹화된 요소 조작
        // - 아래와 같이 구성한다면, 필터를 만족하는 요소가 어벗을 경우 Map에서 Key 자체가 사라진다.
        Map<Type, List<Dish>> caloricDishesByTypeBadCase = dishes.stream().filter(dish -> dish.calories() > 500)
                .collect(groupingBy(Dish::type));

        System.out.println("caloricDishesByTypeBadCase = " + caloricDishesByTypeBadCase);

        //TODO 이렇게 구성하면 키가 안 사라짐 (그룹화된 요소와 필터링된 요소를 재 그룹화)
        Map<Type, List<Dish>> caloricDishesByTypeGoodCase = dishes.stream().collect(groupingBy(Dish::type,
                filtering(dish -> dish.calories() > 500, toList())));  // 각 그룹의 요소와, 필터링된 요소를 재그룹화

        System.out.println("caloricDishesByTypeGoodCase = " + caloricDishesByTypeGoodCase);


        //TODO 그룹의 각 요리를 관련 이름 목록으로 변환
        Map<Type, List<String>> dishNamesByType = dishes.stream()
                .collect(groupingBy(Dish::type, mapping(Dish::name, toList())));

        System.out.println("dishNamesByType = " + dishNamesByType);


        //TODO flatMapping 컬렉터 이용하기
        // - 각 요리에서 tagList를 얻고, 내부 Stream이 되기 때문에, 평면화 해줘야 한다.
        // - flatMapping 연산 결과를 수집해서 리스트가 아니라 집합으로 그룹화하여 중복 태그를 제거한다.
        Map<Type, Set<String>> dishNamesByType2 = dishes.stream().collect(groupingBy(Dish::type,
                flatMapping(dish -> dishTags.get(dish.name()).stream(), toSet())));

        System.out.println("dishNamesByType2 = " + dishNamesByType2);

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
