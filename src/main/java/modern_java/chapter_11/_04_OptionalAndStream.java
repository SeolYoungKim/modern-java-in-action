package modern_java.chapter_11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class _04_OptionalAndStream {
    public static void main(String[] args) {

    }

    //TODO Optional의 stream()은 Optional 안에 값이 존재하는지 여부를 판단한다.
    // 값이 존재하면 Stream.of(value)로 값을 넣어서 반환해주고,
    // 값이 없으면 Stream.empty()를 반환해준다.
    // 값이 존재하는지 여부를 판단 해주기 때문에, filter(Optional::isPresent) -> map(Optional::get)과 똑같은 역할을 한다.
    public static Set<String> getCarInsuranceNames(List<Person> people) {
        return people.stream()
                .map(Person::car)
                .map(optCar -> optCar.flatMap(Car::insurance))
                .map(optIns -> optIns.map(Insurance::name))
//                .map(Optional::stream)  // Stream<String>을 뱉는다. 그래서 map을 쓰면 Stream<Stream<String>>이 됨
                .flatMap(Optional::stream)  // Stream<String>으로 평준화
                .collect(Collectors.toSet());
    }

    public static Set<String> getCarInsuranceNamesBadCase(List<Person> people) {
        return people.stream()
                .map(Person::car)
                .map(optCar -> optCar.flatMap(Car::insurance))
                .map(optIns -> optIns.map(Insurance::name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
