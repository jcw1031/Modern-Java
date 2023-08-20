package woopaca.chapter09;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        /*ProcessingObject<String> headerTextProcessing = new HeaderTextProcessing();
        ProcessingObject<String> spellCheckerProcessing = new SpellCheckerProcessing();
        headerTextProcessing.setSuccessor(spellCheckerProcessing);
        String result = headerTextProcessing.handle("Aren't labdas really sexy?!!");
        System.out.println(result);*/

        UnaryOperator<String> headerTextProcessing =
                text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                text -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerTextProcessing.andThen(spellCheckerProcessing);
        String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

    static class HeaderTextProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    static class SpellCheckerProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }

    static abstract class ProcessingObject<T> {

        protected ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }
}
