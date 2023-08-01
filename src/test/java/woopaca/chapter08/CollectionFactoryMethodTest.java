package woopaca.chapter08;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionFactoryMethodTest {

    @Test
    void test() {
        int[] array = {1, 2, 3};
        List<Integer> listA = new ArrayList<>();
        listA.add(4);
        List<Integer> integers = List.of(1, 2, 3);
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        System.out.println("listA = " + listA);

        List<Integer> list = Arrays.asList(1, 2, 3);
    }
}
