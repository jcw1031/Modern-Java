package woopaca.chapter06;

import woopaca.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

public class CollectorExample {

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

    public void maxAndMin() {
        Comparator<Dish> dishCaloriesComparator =
                comparingInt(Dish::getCalories);

        Optional<Dish> mostCaloriesDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));
    }

    public void summarizingExample() {
        int totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));

        Double avgCalories = menu.stream()
                .collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);
    }

    public void joiningExample() {
        String shortMenu = menu.stream()
                .map(Dish::getName).collect(joining());

        String shortMenuWithSeparator = menu.stream()
                .map(Dish::getName).collect(joining(", "));
    }

    public void reducingExample() {
        int totalCalories = menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    public void groupingExample() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    }
                    if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    }
                    return CaloricLevel.FAT;
                }));

        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            }
                            if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            }
                            return CaloricLevel.FAT;
                        })));

        Map<Dish.Type, Optional<Dish>> mostCaloricByTypeOptional = menu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)), Optional::get
                        )));
    }

    public void partitioningFunctionExample() {
        Map<Boolean, List<Dish>> partitioningMenu = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));

        Map<Boolean, List<Integer>> primes = partitionPrimes(100);
        System.out.println("primes = " + primes);
    }

    private Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}
