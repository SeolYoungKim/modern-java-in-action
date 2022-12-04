package modern_java.chapter_03;

import modern_java.chapter_02.Apple;
import modern_java.chapter_02.Color;
import modern_java.chapter_03.Pet.Cat;
import modern_java.chapter_03.Pet.Dog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class _07_Example {

    static Map<String, Function<Integer, Pet>> map = new HashMap<>();

    static {
        map.put("dog", Dog::new);
        map.put("cat", Cat::new);
    }

    public static Pet giveMePet(String pet, Integer age) {
        return map.get(pet.toLowerCase())
                .apply(age);
    }

    public static void main(String[] args) {
        // case1
        List<Integer> weight = List.of(7, 3, 4, 10);
        List<Apple> appleList = map(weight, Apple::new);
        System.out.println(appleList);

        // case2
        List<Color> colors = List.of(Color.RED, Color.RED, Color.GREEN, Color.RED);
        List<Apple> appleList2 = map(colors, weight, Apple::new);
        System.out.println(appleList2);

        // case3 : 생성을 map에 아예 위임 해버릴수도 있다.
        Pet dog5 = giveMePet("dog", 5);
        Pet dog10 = giveMePet("dog", 10);
        System.out.println(dog5);
        System.out.println(dog10);

        Pet cat5 = giveMePet("cat", 5);
        Pet cat10 = giveMePet("cat", 10);
        System.out.println(cat5);
        System.out.println(cat10);
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> apples = new ArrayList<>();
        for (Integer integer : list) {
            apples.add(f.apply(integer));
        }

        return apples;
    }

    public static List<Apple> map(List<Color> colors, List<Integer> weights, BiFunction<Color, Integer, Apple> f) {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            apples.add(f.apply(colors.get(i), weights.get(i)));
        }

        return apples;
    }


}
