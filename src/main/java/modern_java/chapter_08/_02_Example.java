package modern_java.chapter_08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import modern_java.Dish;
import modern_java.Dish.Type;
import modern_java.Menu;

public class _02_Example {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //TODO 1. 특정 조건일 때 제거
        List<Dish> dishes = new ArrayList<>(Menu.DISHES);
        dishes.removeIf(Dish::isVegetarian);

        System.out.println("dishes = " + dishes);

        //TODO 2. replaceAll
        List<Dish> menu = new ArrayList<>(Menu.DISHES);
        menu.replaceAll(dish -> new Dish("이것이 요리다", false, 1500, Type.MEAT));

        System.out.println("menu = " + menu);

        //TODO 3. Map forEach
        HashMap<String, Integer> map = new HashMap<>();
        map.put("kim", 30);
        map.put("sy", 20);
        map.put("p", 40);

        map.forEach((key, val) -> System.out.println("name : " + key + " | age : " + val));


        //TODO 4. 정렬
        Map<String, String> names = Map.ofEntries(
                Map.entry("kim", "sy"),
                Map.entry("park", "js"),
                Map.entry("Um", "js")
        );

        names.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        names.entrySet()
                .stream()
                .sorted(Entry.comparingByValue())
                .forEachOrdered(System.out::println);


        //TODO 5. getOrDefault
        String kim = names.getOrDefault("kim", "null");
        String lee = names.getOrDefault("lee", "null");

        System.out.println(kim);
        System.out.println(lee);


        //TODO 6. 계산 패턴
        // - computeIfAbsent
        Map<String, byte[]> dataToHash = new HashMap<>();
        List<String> lines = List.of("kkk", "fff", "askldjf");

        lines.forEach(line -> dataToHash.computeIfAbsent(line,  // 맵에서 찾을 키
                _02_Example::calculateDigest));  // 없으면 동작 실행

        System.out.println(dataToHash);

        Map<String, List<String>> friendsToMovies = new HashMap<>();
        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>())  // 키가 없으면 ArrayList 추가
                .add("Star Wars");  // 이후 List에 더함.
        System.out.println("friendsToMovies = " + friendsToMovies);


        //TODO - computeIfPresent
        // 현재 키와 관련된 값이 맵에 존재하며, null이 아닐 때만 새 값을 계산
        // - 해당 메서드를 이용하여 value에 null을 할당하면 key가 지워짐
        friendsToMovies.computeIfAbsent("kim", name -> new ArrayList<>()).add("pokemon");
        friendsToMovies.computeIfAbsent("lee", name -> new ArrayList<>()).add("digimon");

        friendsToMovies.computeIfPresent("Raphael", (key, val) -> null);
        friendsToMovies.computeIfPresent("kim", (key, val) -> List.of("나머지 두개는 지워지고 얘만 남는다."));
        friendsToMovies.computeIfPresent("lee", (key, val) -> null);
        System.out.println(friendsToMovies);


        //TODO 7. 삭제는 remove로!
        friendsToMovies.computeIfAbsent("park", name -> new ArrayList<>()).add("곧 지워질 운명");
        System.out.println(friendsToMovies);

        friendsToMovies.remove("park");
        System.out.println(friendsToMovies);

        friendsToMovies.computeIfAbsent("choi", name -> new ArrayList<>()).add("얘도 곧 간다");

        friendsToMovies.remove("choi", List.of("값이 제대로 매핑되어야만 지워짐"));
        System.out.println("값 똑바로 안쓰면 안지워짐 : " + friendsToMovies);

        friendsToMovies.remove("choi", List.of("얘도 곧 간다"));
        System.out.println(friendsToMovies);
    }

    private final static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] calculateDigest(String key) {
        return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
    }
}
