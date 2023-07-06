package woopaca.chapter01;

import java.util.ArrayList;
import java.util.List;

public class Section03 {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(Color.RED, 100));
        apples.add(new Apple(Color.RED, 200));
        apples.add(new Apple(Color.GREEN, 170));
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

class Apple {

    private Color color;
    private int weight;

    public Apple() {
    }

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return apple.getColor().equals(Color.GREEN);
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}

enum Color {

    RED, GREEN
}

interface Predicate<T> {

    boolean test(T t);
}