package woopaca.chapter03;

import woopaca.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class LambdaExample {

    public static void main(String[] args) throws IOException {
        Comparator<Apple> byWeight = new Comparator<>() {
            @Override
            public int compare(Apple apple1, Apple apple2) {
                return apple1.getWeight().compareTo(apple2.getWeight());
            }
        };

        Comparator<Apple> byWeightLambda =
                (apple1, apple2) -> apple1.getWeight().compareTo(apple2.getWeight());

        String oneLines = processFile((BufferedReader br) -> br.readLine());
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    public static String processFile(BufferedReaderProcessor bufferedReaderProcessor)
            throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return bufferedReaderProcessor.process(br);
        }
    }
}

@FunctionalInterface
interface BufferedReaderProcessor {

    String process(BufferedReader br) throws IOException;
}
