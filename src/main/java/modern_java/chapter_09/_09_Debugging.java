package modern_java.chapter_09;

import java.util.List;

public class _09_Debugging {
    public static void main(String[] args) {
//        List<Point> points = Arrays.asList(new Point(12, 2), null);
//        points.stream().map(Point::getX).forEach(System.out::println);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.stream().map(_09_Debugging::divideByZero).forEach(System.out::println);
    }

    public static int divideByZero(int n) {
        return n / 0;
    }
}
