package modern_java.chapter_13._01_api_ver1;

public interface Resizable extends Draw {
    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);
}
