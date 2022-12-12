package modern_java.chapter_13._03_application_of_default_method;

public interface Rotatable {
    void setRotationAngle(int angleInDegrees);
    int getRotationAngle();

    default void rotateBy(int angleInDegrees) {
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
    }
}
