package modern_java.chapter_11;

import java.util.Map;
import java.util.Optional;

public class _08_OptionalPractice {
    public static void main(String[] args) {
        //TODO 1. 잠재적으로 null이 될 수 있는 대상을 Optional로 감싸기
        Map<String, String> names = Map.of("나는", "딱구킴");
        String you = names.get("너는");  // NullPointerException 위험
        Optional<String> youOpt = Optional.ofNullable(names.get("너는"));

        //TODO 2. 예외 & Optional -> OptionalUtility를 만들어 해결
        Optional<Integer> integer = stringToInt("132423---");
        System.out.println(integer);

        //TODO 3. 기본형 Optional은 사용하지 마라
        // - OptionalInt, OptionalLong, OptionalDouble ...
        // - Optional의 최대 요소 수는 한 개이므로, 기본형 특화 클래스로 성능을 개선할 수 없다.
        // - flatMap, filter 등을 지원하지 않음.
        // - 기본형 Optional이 생성한 결과는 다른 Optional들과 혼용 불가
        // - OptionalInt를 반환한다면, 이를 다른 Optional의 flatMap에 메서드 참조로 전달할 수 없음
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
