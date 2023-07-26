package woopaca.chapter05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {

    public void stream() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<Object> emptyStream = Stream.empty();
    }

    public void nullableStream() {
        Stream<String> homeNullableStream = Stream.ofNullable(System.getProperty("home"));

        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
    }

    public void arrayStream() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream stream = Arrays.stream(numbers);
        int sum = stream.sum();
    }

    public void fileStream() {
        long uniqueWordsCount = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWordsCount = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void infiniteStream() {
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .map(n -> n[0])
                .limit(20)
                .forEach(System.out::println);

        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);
    }

    public void statefulSideEffect() {
        IntSupplier fibonacci = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fibonacci)
                .limit(10)
                .forEach(System.out::println);
    }
}
