package woopaca.chapter01;

import org.junit.jupiter.api.Test;

import java.util.List;

class StreamTestTest {

    @Test
    void test() {
        StreamTest.groupExpensiveTransactionIntoCurrencies(List.of(new Transaction(1000, new Currency())));
    }
}