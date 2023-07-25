package woopaca.chapter05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntStreamExampleTest {

    private final IntStreamExample intStreamExample = new IntStreamExample();

    @Test
    void test() {
        intStreamExample.intStream();
    }
}