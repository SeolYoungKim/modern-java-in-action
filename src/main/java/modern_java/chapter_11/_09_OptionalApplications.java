package modern_java.chapter_11;

import java.util.Optional;
import java.util.Properties;

public class _09_OptionalApplications {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        int a = readDuration(properties, "a");
        int b = readDuration(properties, "b");
        int c = readDuration(properties, "c");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static int readDuration(Properties properties, String name) {
        return property(properties, name)
                .flatMap(_09_OptionalApplications::valueToInt)
                .filter(integer -> integer > 0)
                .orElse(0);
    }

    public static Optional<String> property(Properties properties, String name) {
        return Optional.ofNullable(properties.getProperty(name));
    }

    public static Optional<Integer> valueToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
}
