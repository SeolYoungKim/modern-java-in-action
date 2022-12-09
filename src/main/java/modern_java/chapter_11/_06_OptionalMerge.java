package modern_java.chapter_11;

import java.util.Optional;

public class _06_OptionalMerge {
    public static void main(String[] args) {

    }

    public static Insurance findCheapestInsurance(Person person, Car car) {
        // 대충 데이터를 비교하고 가장 싼 회사를 찾는 로직
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person,
            Optional<Car> car) {
//        if (person.isPresent() && car.isPresent()) {
//            return Optional.of(findCheapestInsurance(person.get(), car.get()));
//        }
//
//        return Optional.empty();

        //TODO Optional의 map, flatMap은 Optional.empty()에 대해 내부의 함수식이 수행되지 않음. (걍 빈 Optional 반환)
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
}
