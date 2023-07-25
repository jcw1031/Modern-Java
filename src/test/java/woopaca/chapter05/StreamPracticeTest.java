package woopaca.chapter05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamPracticeTest {

    private final Trader raoul = new Trader("Raoul", "Cambridge");
    private final Trader mario = new Trader("Mario", "Milan");
    private final Trader alan = new Trader("Alan", "Cambridge");
    private final Trader brian = new Trader("Brian", "Cambridge");

    private final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    private final StreamPractice streamPractice = new StreamPractice();

    @Test
    void first() {
        List<Transaction> result = streamPractice.first(transactions);
        System.out.println("result = " + result);
        assertEquals(new Transaction(brian, 2011, 300), result.get(0));
    }

    @Test
    void second() {
        List<String> result = streamPractice.second(transactions);
        System.out.println("result = " + result);
        assertTrue(result.containsAll(Arrays.asList("Milan", "Cambridge")));
    }

    @Test
    void third() {
        List<Trader> result = streamPractice.third(transactions);
        System.out.println("result = " + result);
        assertEquals(new Trader("Alan", "Cambridge"), result.get(0));
    }

    @Test
    void fourth() {
//        List<String> result = streamPractice.fourth(transactions);
        String result = streamPractice.fourth(transactions);
        System.out.println("result = " + result);
        assertEquals("AlanBrianMarioRaoul", result);
    }

    @Test
    void fifth() {
        boolean isTraderExistsInMilan = streamPractice.fifth(transactions);
        System.out.println("isTraderExistsInMilan = " + isTraderExistsInMilan);
        assertTrue(isTraderExistsInMilan);
    }

    @Test
    void sixth() {
        streamPractice.sixth(transactions);
    }

    @Test
    void seventh() {
        int result = streamPractice.seventh(transactions);
        System.out.println("result = " + result);
        assertEquals(1000, result);
    }

    @Test
    void eighth() {
        int result = streamPractice.eighth(transactions);
        System.out.println("result = " + result);
        assertEquals(300, result);
    }
}