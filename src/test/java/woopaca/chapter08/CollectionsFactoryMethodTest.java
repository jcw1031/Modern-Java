package woopaca.chapter08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsFactoryMethodTest {

    private final CollectionsFactoryMethod collectionsFactoryMethod = new CollectionsFactoryMethod();

    @Test
    void test() {
        collectionsFactoryMethod.list();
    }
}