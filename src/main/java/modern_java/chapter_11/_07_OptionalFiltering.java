package modern_java.chapter_11;

import java.util.Optional;

public class _07_OptionalFiltering {
    public static void main(String[] args) {
        // 보험회사 이름이 '딱구컴퍼니'인지 확인해야 한다고 가정하자
    }

    public static void check(Insurance insurance) {
        if (insurance != null && "딱구컴퍼니".equals(insurance.name())) {
            System.out.println("ok");
        }
    }

    public static void checkOptional(Insurance insurance) {
        Optional<Insurance> optInsurance = Optional.of(insurance);
        optInsurance.filter(insurance1 -> "딱구컴퍼니".equals(insurance.name()))
                .ifPresent(insurance1 -> System.out.println("ok"));
    }
}
