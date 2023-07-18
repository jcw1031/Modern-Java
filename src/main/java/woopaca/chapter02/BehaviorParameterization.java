package woopaca.chapter02;

import woopaca.Apple;
import woopaca.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Behavior Parameterization
 */
public class BehaviorParameterization {

    public static void main(String[] args) {
        var inventory =
                Arrays.asList(new Apple(100, Color.GREEN), new Apple(200, Color.RED), new Apple(230, Color.GREEN));
        List<Apple> greenApples = AppleFilter.filterApples(inventory, new AppleGreenColorPredicate());
        List<Apple> redApplesAnonymousClass =
                AppleFilter.filterApples(inventory, new Predicate<Apple>() {
                    @Override
                    public boolean test(Apple apple) {
                        return apple.getColor().equals(Color.RED);
                    }
                });
        List<Apple> redApples =
                AppleFilter.filterApples(inventory, apple -> apple.getColor().equals(Color.RED));
        System.out.println("greenApples = " + greenApples);
        System.out.println("redApples = " + redApples);

        AppleUtils.prettyPrintApple(inventory, apple -> {
            String weight = apple.getWeight() > 150 ? "heavy" : "light";
            String color = apple.getColor().equals(Color.GREEN) ? "green" : "red";
            return weight + " " + color + " apple";
        });
    }
}

class AppleFilter {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(Color.GREEN)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color) || !flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

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

class AppleHeavyWeightPredicate implements Predicate<Apple> {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

class AppleGreenColorPredicate implements Predicate<Apple> {

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals(Color.GREEN);
    }
}

class AppleUtils {

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
}

@FunctionalInterface
interface AppleFormatter {

    String accept(Apple apple);
}

class AppleFancyFormatter implements AppleFormatter {

    @Override
    public String accept(Apple apple) {
        String weight = apple.getWeight() > 150 ? "heavy" : "light";
        String color = apple.getColor().equals(Color.GREEN) ? "green" : "red";
        return weight + " " + color + " apple";
    }
}

class Filter {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
