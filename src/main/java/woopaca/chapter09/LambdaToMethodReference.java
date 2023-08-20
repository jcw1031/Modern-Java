package woopaca.chapter09;

import woopaca.Apple;
import woopaca.Fruit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaToMethodReference {

    private static final List<Apple> INVENTORY =
            Arrays.asList(new Apple(100), new Apple(200), new Apple(150));

    public static void main(String[] args) {
        INVENTORY.sort((apple1, apple2) ->
                apple1.getWeight().compareTo(apple2.getWeight()));
        INVENTORY.sort(Comparator.comparing(Fruit::getWeight));
    }
}
