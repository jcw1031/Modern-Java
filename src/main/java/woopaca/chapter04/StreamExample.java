package woopaca.chapter04;

import woopaca.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamExample {

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

    public void sortAndExtractNameLowCaloricDishes() {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        lowCaloricDishes.sort(comparing(Dish::getCalories));

        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish lowCaloricDish : lowCaloricDishes) {
            lowCaloricDishesName.add(lowCaloricDish.getName());
        }
    }

    public void sortAndExtractNameLowCaloricDishesStream() {
        List<String> lowCaloricDishesName = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    public void sortAndExtractNameLowCaloricDishesParallelStream() {
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    public void pipelineExample() {
        List<String> threeHighCaloricDishesName = menu.stream() // menu에서 스트림 획득
                .filter(dish -> dish.getCalories() < 300) // 고칼로리 요리 필터링
                .map(Dish::getName) // 요리명 추출
                .limit(3) // 선착순 세 개만 선택
                .collect(toList()); // 결과를 다른 리스트로 저장
        System.out.println("threeHighCaloricDishesName = " + threeHighCaloricDishesName);
    }

    public void externalIterationAndInternalIteration() {
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }

        List<String> highCaloricDishesStream = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
    }
}
