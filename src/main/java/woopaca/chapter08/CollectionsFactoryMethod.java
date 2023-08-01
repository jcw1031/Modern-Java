package woopaca.chapter08;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsFactoryMethod {

    public void list() {
        List<Integer> list = List.of(1, 2, 3);
        try {
            list.add(4);
        } catch (Exception e) {
            assert e instanceof UnsupportedOperationException;
        }
    }

    public void set() {
        Set<Integer> set = Set.of(1, 2, 3);
        try {
            set.add(4);
        } catch (Exception e) {
            assert e instanceof UnsupportedOperationException;
        }
        try {
            set.add(1);
        } catch (Exception e) {
            assert e instanceof IllegalArgumentException;
        }
    }

    public void map() {
        Map<Integer, String> map = Map.of(1, "1", 2, "2", 3, "3");
        Map<String, Integer> mapEntries = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26)
        );
    }
}
