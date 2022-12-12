package modern_java.chapter_13._03_application_of_default_method;

public interface Resizeable {
    int getWidth();
    int getHeight();

    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);

    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }
}
