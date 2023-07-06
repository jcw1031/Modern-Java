package woopaca.chapter01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Section01 {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(100, 100));
        inventory.add(new Apple(200, 200));
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

    static class Apple {

        private Integer weight;
        private Integer price;

        public Apple() {
        }

        public Apple(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
