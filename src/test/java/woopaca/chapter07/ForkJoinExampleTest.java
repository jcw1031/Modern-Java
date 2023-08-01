package woopaca.chapter07;

import org.junit.jupiter.api.Test;

class ForkJoinExampleTest {

    @Test
    void test() {
        long result = ForkJoinExample.forkJoinSum(100_000_000);
        System.out.println("result = " + result);
    }
}