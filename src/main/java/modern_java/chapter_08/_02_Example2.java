package modern_java.chapter_08;

import java.util.HashMap;
import java.util.Map;

public class _02_Example2 {

    public static void main(String[] args) {
        //TODO 1. replaceAll
        Map<String, String> favoriteMovies = new HashMap<>();
        favoriteMovies.put("Raphael", "Star wars");
        favoriteMovies.put("Olivia", "james bond");

        favoriteMovies.replaceAll((friends, movie) -> movie.toUpperCase());
        System.out.println(favoriteMovies);

        //TODO 2. putAll - 중복된 키가 없을 때
        Map<String, String> family = Map.of(
                "Teo", "Star Wars",
                "Cristina", "James Bond");

        Map<String, String> friends = Map.of(
                "Raphael", "Star Wars");

        HashMap<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);

        System.out.println(everyone);


        //TODO 3. merge - 중복된 키가 있을 때
        Map<String, String> familyDuplicated = Map.of(
                "Teo", "Star Wars",
                "Cristina", "James Bond");

        Map<String, String> friendsDuplicated = Map.of(
                "Raphael", "Star Wars",
                "Cristina", "Matrix");

        HashMap<String, String> everyoneDupl = new HashMap<>(familyDuplicated);
        friendsDuplicated.forEach((k, v) ->
                everyoneDupl.merge(k, v, (v1, v2) -> v1 + " & " + v2));

        System.out.println(everyoneDupl);

        //TODO merge로 초기화 검사를 구현할 수도 있음
        HashMap<String, Long> moviesToCount = new HashMap<>();
        String movieName = "JamesBond";
        moviesToCount.merge(movieName, 1L,
                (key, count) -> count + 1L);  // 맵 안에 movieName이 있으면 증가후 머지, 없으면 걍 머지

        // merge(key, value, Bifunction)
        // value : key와 연관된 기존 값에 합쳐질 null이 아닌 값
        //         값이 없거나 키에 null값이 연관되어 있다면 이 값을 키와 연결함
        // 초기 key의 반환 값이 null이므로, 처음에는 value에 입력된 1L이라는 값을 사용한다.
        // 그 다음부터는 값이 1로 초기화 되어있으므로 BiFunction을 적용해 값을 증가시킨다.
    }
}
