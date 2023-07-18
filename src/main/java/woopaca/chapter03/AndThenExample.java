package woopaca.chapter03;

import java.util.function.Function;

public class AndThenExample {

    public static void main(String[] args) {
        Function<String, String> transformationPipeline = ((Function<String, String>) Letter::addHeader)
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        print("지찬우 labda", transformationPipeline);
    }

    private static void print(String text, Function<String, String> function) {
        String result = function.apply(text);
        System.out.println("result = " + result);
    }
}

class Letter {

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
