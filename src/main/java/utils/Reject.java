package utils;

public class Reject {

    public static void ifFalse(boolean condition) {
        if(!condition) {
            always();
        }
    }

    public static void always() {
        throw new IllegalStateException();
    }
}
