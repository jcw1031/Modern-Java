package woopaca.chapter05;

import woopaca.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamExample {

    private final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public void intStream() {
        long start = System.nanoTime();
        int sum = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        long end = System.nanoTime();
        System.out.println(end - start);

        start = System.nanoTime();
        int sumIntStream = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        end = System.nanoTime();
        System.out.println(end - start);
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
    }

    public void optionalInt() {
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
    }

    public void pythagoras() {
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
