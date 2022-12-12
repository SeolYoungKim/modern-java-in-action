package modern_java.chapter_13._03_application_of_default_method;

public interface Moveable {
    int getX();
    int getY();

    void setX(int x);
    void setY(int y);

    default void moveHorizontally(int distance) {
        setX(getX() + distance);
    }

    default void moveVertically(int distance) {
        setY(getY() + distance);
    }
}
