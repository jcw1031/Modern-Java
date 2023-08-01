package woopaca.chapter07;

import java.util.stream.Stream;

public class CustomSpliterator {

    public int countWordsIteratively(String input) {
        int count = 0;
        boolean lastSpace = true;
        for (char bit : input.toCharArray()) {
            if (Character.isWhitespace(bit)) {
                lastSpace = true;
                continue;
            }
            if (lastSpace) {
                count++;
            }
            lastSpace = false;
        }
        return count;
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter =
                stream.reduce(new WordCounter(0, true),
                        WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}

class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character bit) {
        if (Character.isWhitespace(bit)) {
            return lastSpace ? this : new WordCounter(counter, true);
        }
        return lastSpace ? new WordCounter(counter + 1, false) : this;
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
