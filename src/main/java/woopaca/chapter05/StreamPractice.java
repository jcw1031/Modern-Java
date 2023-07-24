package woopaca.chapter05;

import woopaca.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

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

    public void predicateFiltering() {
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
    }

    public void distinctFiltering() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    public void takeWhileAndDropWhile() {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        List<Dish> slicedMenuA = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() <= 320)
                .collect(Collectors.toList());
        System.out.println("filteredMenu = " + slicedMenuA);

        List<Dish> slicedMenuB = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() <= 320)
                .collect(Collectors.toList());
        System.out.println("slicedMenuB = " + slicedMenuB);
    }

    public void skip() {
        List<Dish> test = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("test = " + test);
    }

    public void map() {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<Integer> dishNamesLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
    }

    public void flatMap() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> characters = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        List<Stream<String>> charactersStream = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public void mapping() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> squares = numbers.stream()
                .map(Math::sqrt)
                .collect(Collectors.toList());
        System.out.println("squares = " + squares);

        List<Integer> numbersA = Arrays.asList(1, 2, 3);
        List<Integer> numbersB = Arrays.asList(3, 4);
        List<int[]> newNumbers = numbersA.stream()
                .flatMap(numberA -> numbersB.stream()
                        .map(numberB -> new int[]{numberA, numberB}))
                .collect(Collectors.toList());
        System.out.print("newNumbers = ");
        for (int[] newNumber : newNumbers) {
            System.out.print(Arrays.toString(newNumber) + ", ");
        }
    }

    public void mappingTest() {
        List<Integer> numbersA = Arrays.asList(1, 2, 3);
        List<Integer> numbersB = Arrays.asList(3, 4);
        List<int[]> newNumbers = numbersA.stream()
                .flatMap(numberA -> numbersB.stream()
                        .map(numberB -> new int[]{numberA, numberB})
                        .filter(numberPair -> (numberPair[0] + numberPair[1]) % 3 == 0))
                .collect(Collectors.toList());
        System.out.print("newNumbers = ");
        for (int[] newNumber : newNumbers) {
            System.out.print(Arrays.toString(newNumber) + ", ");
        }
    }

    public void matchExample() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!");
        }

        if (menu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("The menu is healthy food!");
        }

        if (menu.stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println("The menu is healthy food!");
        }
    }

    public void findExample() {
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
}
