package woopaca.chapter03;

import woopaca.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
}

class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
