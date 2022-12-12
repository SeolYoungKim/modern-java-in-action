package modern_java.chapter_13._02_api_ver2;

import java.util.List;

public class Utils {
    public static void paint(List<Resizable> l) {
        l.forEach(resizable -> {
            resizable.setAbsoluteSize(42, 42);
            resizable.draw();
        });
    }
}
