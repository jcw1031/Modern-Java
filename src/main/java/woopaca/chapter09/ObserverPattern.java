package woopaca.chapter09;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London... " + tweet);
            }
        });
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });
        feed.notifyObservers("The queen said her favourite book is Modern Java in Action!");
    }

    interface Observer {
        void notify(String tweet);
    }

    interface Subject {
        void registerObserver(Observer observer);

        void notifyObservers(String tweet);
    }

    static class Feed implements Subject {

        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(observer -> observer.notify(tweet));
        }
    }
}
