package woopaca.chapter05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeStreamTest {

    private final MakeStream makeStream = new MakeStream();

    @Test
    void test() {
        makeStream.infiniteStream();
    }
}