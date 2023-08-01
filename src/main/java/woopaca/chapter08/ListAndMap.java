package woopaca.chapter08;

import woopaca.chapter05.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class ListAndMap {

    public static void main(String[] args) {
        mapRemove();
    }

    public void removeIfMethod() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.removeIf(transaction ->
                Character.isDigit(transaction.getTrader().getCity().charAt(0)));
    }

    public void replaceAllMethod() {
        List<String> referenceCodes = Arrays.asList("a12", "C14", "b13");
        referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        ListIterator<String> iterator = referenceCodes.listIterator();
        while (iterator.hasNext()) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }

        referenceCodes.replaceAll(code ->
                Character.toUpperCase(code.charAt(0)) + code.substring(1));
    }

    public void mapForEach() {
        Map<String, Integer> ageOfFriends =
                Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

        ageOfFriends.forEach((friend, age) ->
                System.out.println(friend + " is " + age + " years old"));
    }

    public void mapSorting() {
        Map<String, String> favouriteMovies = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"),
                entry("Olivia", "James Bond")
        );

        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }

    public void mapGetOrDefault() {
        Map<String, String> favouriteMovies = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"),
                entry("Olivia", "James Bond")
        );

        System.out.println(favouriteMovies.getOrDefault("Thibaut", "Matrix"));
    }

    public void mapCompute() {
        Map<String, List<String>> friendsToMovies = new HashMap<>();
        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>())
                .add("Star Wars");
    }

    public static void mapRemove() {
        Map<String, String> favouriteMovies = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"),
                entry("Olivia", "James Bond")
        );

        favouriteMovies.remove("Raphael", "Jack Reacher 2");
    }

    public void merge() {
        HashMap<String, String> mapA = new HashMap<>();
        HashMap<String, String> mapB = new HashMap<>();
        mapA.forEach((key, value) ->
                mapB.merge(key, value, (value1, value2) -> value1 + "&" + value2));
    }
}
