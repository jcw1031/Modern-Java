package woopaca.chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class LambdaExample {

    public static void main(String[] args) {
        Comparator<Apple> byWeight = new Comparator<>() {
            @Override
            public int compare(Apple apple1, Apple apple2) {
                return apple1.getWeight().compareTo(apple2.getWeight());
            }
        };

        Comparator<Apple> byWeightLambda =
                (apple1, apple2) -> apple1.getWeight().compareTo(apple2.getWeight());
    }

    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }
}

class Apple {

    private Integer weight;
    private Color color;

    public Apple() {
    }

    public Apple(Integer weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}

enum Color {

    RED, GREEN
}
