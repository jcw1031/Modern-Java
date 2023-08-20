package woopaca.chapter08;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

    public void reduceValuesMethod() {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        long parallelThreshold = 1;
        Optional<Long> maxValue =
                Optional.ofNullable(map.reduceValues(parallelThreshold, Long::max));
    }
}
