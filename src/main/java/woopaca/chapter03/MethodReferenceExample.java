package woopaca.chapter03;

import woopaca.Apple;
import woopaca.Color;
import woopaca.Fruit;
import woopaca.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceExample {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

    public void methodReference() {
        List<String> nonEmptyStringLambda = filter(Arrays.asList("a", "b", ""), (String input) -> isValidName(input)); // lambda
        List<String> nonEmptyStringMethodReference = filter(Arrays.asList("a", "b", ""), this::isValidName); // method reference

        List<String> list = Arrays.asList("a", "B", "C", "d");
        list.sort(String::compareToIgnoreCase);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private boolean isValidName(String s) {
        return Character.isUpperCase(s.charAt(0));
    }

    public void test() {

        Consumer<Object> objectConsumer = ClassName::staticMethod;
        Supplier<ClassName> supplier = ClassName::new;

        Function<Integer, ClassName> function = ClassName::new;

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        BiFunction<Integer, Color, Apple> biFunction = Apple::new;
        Apple apple = biFunction.apply(100, Color.GREEN);
    }

    public List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer integer : list) {
            result.add(function.apply(integer));
        }
        return result;
    }
}

class ClassName {

    Integer integer;

    public ClassName() {
    }

    public ClassName(Integer integer) {
        this.integer = integer;
    }

    public static void staticMethod(Object args) {
    }
}