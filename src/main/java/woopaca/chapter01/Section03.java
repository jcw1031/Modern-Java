package woopaca.chapter01;

import woopaca.Apple;
import woopaca.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Section03 {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(100, Color.RED));
        apples.add(new Apple(200, Color.RED));
        apples.add(new Apple(170, Color.GREEN));
        FilterTest.filterApples(apples, apple -> apple.getColor().equals(Color.GREEN));
    }
}

class FilterTest {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(Color.GREEN)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }

        return result;
    }

    // ---------------------------------------------------------------
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }
}

