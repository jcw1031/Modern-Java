package woopaca.chapter01;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Section05 {

    public static void main(String[] args) {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        Stream<Integer> collectionStream = collection.stream();

        Stream<Integer> arrayStream = Stream.of(1, 2, 3);
        Stream<Integer> arrayStreamFull = Arrays.stream(new Integer[]{1, 2, 3});
        Stream<Integer> arrayStreamPart = Arrays.stream(new Integer[]{1, 2, 3}, 1, 3);
    }

    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }
}


