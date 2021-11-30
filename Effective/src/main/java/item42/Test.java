package item42;

public class Test {
    String message = "hi";
    Runnable r = () -> {
        System.out.println(this.message);
    };
}
