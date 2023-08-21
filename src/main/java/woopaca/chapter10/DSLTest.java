package woopaca.chapter10;

import woopaca.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;

public class DSLTest {

    private static final List<Person> PEOPLE = new ArrayList<>();

    static {
        PEOPLE.add(new Person(20));
        PEOPLE.add(new Person(15));
        PEOPLE.add(new Person(40));
    }

    public static void main(String[] args) {
        // 1
        Collections.sort(PEOPLE, new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getAge() - person2.getAge();
            }
        });

        // 2
        Collections.sort(PEOPLE, (person1, person2) -> person1.getAge() - person2.getAge());

        // 3
        Collections.sort(PEOPLE, Comparator.comparing(person -> person.getAge()));

        // 4
        Collections.sort(PEOPLE, Comparator.comparing(Person::getAge));

        // 5
        PEOPLE.sort(Comparator.comparing(Person::getAge));

        List<Car> cars = new ArrayList<>();
        Map<String, Map<Color, List<Car>>> carsByBrandAndColor = cars.stream()
                .collect(groupingBy(Car::getBrand, groupingBy(Car::getColor)));

        Collector<? super Car, ?, Map<String, Map<Color, List<Car>>>> carGroupingCollector =
                GroupingBuilder.groupOn(Car::getColor).after(Car::getBrand).getCollector();
    }
}

class GroupingBuilder<T, D, K> {

    private final Collector<? super T, ?, Map<K, D>> collector;

    private GroupingBuilder(Collector<? super T, ?, Map<K, D>> collector) {
        this.collector = collector;
    }

    public Collector<? super T, ?, Map<K, D>> getCollector() {
        return collector;
    }

    public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier) {
        return new GroupingBuilder<>(groupingBy(classifier, collector));
    }

    public static <T, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
        return new GroupingBuilder<>(groupingBy(classifier));
    }
}

class Person {

    private final int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

class Car {

    private final Color color;
    private final String brand;

    Car(Color color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }
}
