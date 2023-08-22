package woopaca.chapter11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OptionalInsteadOfNull {

    public Set<String> getCarInsuranceName(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optionalCar -> optionalCar.flatMap(Car::getInsurance))
                .map(optionalInsurance -> optionalInsurance.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }
}

class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {

    private String name;

    public String getName() {
        return name;
    }
}
