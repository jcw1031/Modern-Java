package woopaca.chapter02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    public static void main(String[] args) {
        ExecutorService executorServiceA = Executors.newCachedThreadPool();
        ExecutorService executorServiceB = Executors.newFixedThreadPool(10);

        executorServiceA.submit(() -> Thread.currentThread().getName());
        executorServiceB.submit(() -> Thread.currentThread().getName());
    }
}
