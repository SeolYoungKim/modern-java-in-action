package modern_java.chapter_09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    void testMoveRightBy() {
        Point point1 = new Point(5, 5);
        Point point2 = point1.moveRightBy(10);

        Assertions.assertEquals(15, point2.getX());
        Assertions.assertEquals(5, point2.getY());
    }

    @Test
    void testComparingTwoPotins() {
        Point point1 = new Point(10, 15);
        Point point2 = new Point(10, 20);
        int actual = Point.compareByXAndThenY.compare(point1, point2);
        assertTrue(actual < 0);
    }
}