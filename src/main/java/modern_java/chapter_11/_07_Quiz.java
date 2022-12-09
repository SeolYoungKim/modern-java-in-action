package modern_java.chapter_11;

import java.util.Optional;

public class _07_Quiz {
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(person1 -> person1.age() >= minAge)
                .flatMap(Person::car)
                .flatMap(Car::insurance)
                .map(Insurance::name)
                .orElse("Unknown");
    }
}
