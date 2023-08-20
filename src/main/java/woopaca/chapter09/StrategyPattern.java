package woopaca.chapter09;

public class StrategyPattern {

    public static void main(String[] args) {
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        boolean b2 = lowerCaseValidator.validate("bbbb");
    }

    static class Validator {

        private final ValidationStrategy validationStrategy;

        Validator(ValidationStrategy validationStrategy) {
            this.validationStrategy = validationStrategy;
        }

        public boolean validate(String s) {
            return validationStrategy.execute(s);
        }
    }

    static class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    static class IsNumeric implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    @FunctionalInterface
    interface ValidationStrategy {

        boolean execute(String s);
    }
}
