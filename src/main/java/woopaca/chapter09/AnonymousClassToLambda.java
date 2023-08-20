package woopaca.chapter09;

public class AnonymousClassToLambda {

    public static void main(String[] args) {
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("AnonymousClassToLambda.execute");
            }
        });

        doSomething((Task) () -> System.out.println("AnonymousClassToLambda.main"));
    }

    public static void doSomething(Runnable runnable) {
        runnable.run();
    }

    public static void doSomething(Task task) {
        task.execute();
    }

    static interface Task {

        void execute();
    }
}
