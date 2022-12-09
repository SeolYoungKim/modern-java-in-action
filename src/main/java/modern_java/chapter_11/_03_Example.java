package modern_java.chapter_11;

import java.util.Optional;

public class _03_Example {
    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        Optional<Car> car = Optional.of(new Car());  //TODO Optional.of 는 null 비허용 -> null이 들어가면 NullPointerException!
        Optional<Object> nullable = Optional.ofNullable(null);  //TODO Optional.ofNullable은 null 허용


        //TODO map으로 Optional 값 추출 및 변환
        String name = null;
        Optional<Insurance> optionalInsurance = Optional.ofNullable(new Insurance());
        Optional<String> optName = optionalInsurance.map(insurance -> insurance.name());


    }

    public static String getCarInsuranceName(Person person) {
        return Optional.of(person)
                .flatMap(Person::car)
                .flatMap(Car::insurance)
                .map(Insurance::name)
                .orElse("없는 보험사");
    }
}
