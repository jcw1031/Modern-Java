package woopaca.chapter03;

import woopaca.Apple;
import woopaca.Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class PracticalUse {

    private final List<Apple> inventory =
            Arrays.asList(new Apple(100), new Apple(200), new Apple(150));

    public void transferCode() {
        inventory.sort(new AppleComparator());
    }

    public void useAnonymousClass() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }

    public void useLambda() {
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing(apple -> apple.getWeight()));
    }

    public void useMethodReference() {
        inventory.sort(comparing(Apple::getWeight));
    }

    public void reverse() {
        inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.sort(comparing((Apple apple) -> apple.getWeight()).reversed());
    }

    public void dualComparator() {
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
    }

    public void compoundPredicate() {
        Predicate<Apple> notRedApple =
                ((Predicate<Apple>) apple -> apple.getColor().equals(Color.RED))
                        .negate();

        Predicate<Apple> redAndHeavyApple =
                ((Predicate<Apple>) apple -> apple.getColor().equals(Color.RED))
                        .and(apple -> apple.getWeight() > 150);

        Predicate<Apple> redAndHeavyAppleOrGreenApple =
                ((Predicate<Apple>) apple -> apple.getColor().equals(Color.RED))
                        .or(apple -> apple.getColor().equals(Color.GREEN));
    }

    public void compoundFunction() {
        Function<Integer, Integer> andThen = ((Function<Integer, Integer>) x -> x + 1)
                .andThen(x1 -> x1 * 2);

        Function<Integer, Integer> compose = ((Function<Integer, Integer>) x -> x + 1)
                .compose(x -> x * 2);
    }
}

class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
