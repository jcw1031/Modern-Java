package woopaca.chapter01;

import woopaca.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Section01 {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(100));
        inventory.add(new Apple(200));
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }
}
