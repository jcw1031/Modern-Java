package woopaca.chapter06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectorExampleTest {

    private final CollectorExample collectorExample = new CollectorExample();

    /**
     * IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
     */
    @Test
    void test() {
        collectorExample.summarizingExample();
        collectorExample.partitioningFunctionExample();
    }
}