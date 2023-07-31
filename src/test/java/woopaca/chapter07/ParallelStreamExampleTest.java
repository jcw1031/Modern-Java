package woopaca.chapter07;

import org.junit.jupiter.api.Test;

class ParallelStreamExampleTest {

    private final ParallelStreamExample parallelStreamExample = new ParallelStreamExample();

    @Test
    void test() {
        for (int i = 0; i < 10; i++) {
            long result = parallelStreamExample.sideEffectParallelSum(10_000_000L);
            System.out.println("result = " + result);
        }
    }
}