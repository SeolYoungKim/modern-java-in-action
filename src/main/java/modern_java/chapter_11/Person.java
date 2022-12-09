package modern_java.chapter_11;

import java.util.Optional;

public class Person {
    private Optional<Car> car;
    private int age;

    public Optional<Car> car() {
        return car;
    }

    public int age() {
        return age;
    }
}
