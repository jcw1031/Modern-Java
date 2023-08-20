package woopaca.chapter09;

import org.junit.jupiter.api.Test;
import woopaca.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LambdaTest {

    @Test
    void testMoveRightBy() throws Exception {
        Point point1 = new Point(5, 5);
        Point point2 = point1.moveRightBy(10);

        assertEquals(15, point2.getX());
        assertEquals(5, point2.getY());
    }

    @Test
    void testComparingTwoPoints() throws Exception {
        Point point1 = new Point(10, 15);
        Point point2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(point1, point2);

        assertTrue(result < 0);
    }

    @Test
    void testMoveAllPointsRightBy() throws Exception {
        List<Point> points = List.of(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints = List.of(new Point(15, 5), new Point(20, 5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

        assertEquals(expectedPoints, newPoints);
    }

    /*@Test
    void testFilter() {
        List<Integer> numbers = List.of(1, 2, 3, 4);
        List<Integer> even = filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = filter(numbers, i -> i < 3);

        assertEquals(List.of(2, 4), even);
        assertEquals(List.of(1, 2), smallerThanThree);
    }*/
}
