package item38;

import java.util.Arrays;
import java.util.Collection;

public class Test {

    public static void main(String[] args) {
        double x = 2;
        double y = 4;
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        System.out.println(opEnumType);
        T[] enumConstants = opEnumType.getEnumConstants();
        System.out.println(enumConstants);
        for (Operation op : opEnumType.getEnumConstants())
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
