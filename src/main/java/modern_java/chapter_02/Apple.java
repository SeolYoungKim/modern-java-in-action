package modern_java.chapter_02;

public class Apple {

    private final Color color;
    private final int weight;

    public Apple() {
        this.color = Color.RED;
        this.weight = 100;
    }

    public Apple(Color color) {
        this(color, 100);
    }

    public Apple(int weight) {
        this(Color.RED, weight);
    }
    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color color() {
        return color;
    }

    public int weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", weight=" + weight +
                '}';
    }
}
