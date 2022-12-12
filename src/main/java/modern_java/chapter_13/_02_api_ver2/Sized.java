package modern_java.chapter_13._02_api_ver2;

public interface Sized {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
