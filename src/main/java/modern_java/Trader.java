package modern_java;

public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String name() {
        return name;
    }

    public String city() {
        return city;
    }

    @Override
    public String toString() {
        return "modern_java.Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
