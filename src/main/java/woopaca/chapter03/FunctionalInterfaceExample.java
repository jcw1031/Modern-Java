package woopaca.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExample {

    public void predicateExample() {
        Predicate<String> nonEmptyStringPredicate = (String input) -> !input.isEmpty();
        List<String> nonEmptyString = filter(Arrays.asList("a", "b", ""), nonEmptyStringPredicate);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public void consumerExample() {
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
    }

    public <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public void functionExample() {
        List<Integer> list = map(Arrays.asList("java", "in", "action"), (String s) -> s.length());
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
