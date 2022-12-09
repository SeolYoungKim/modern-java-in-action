package modern_java.chapter_11;

import java.util.Optional;

public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> insurance() {
        return insurance;
    }
}
