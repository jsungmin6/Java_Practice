package item55;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        if(c.isEmpty())
            return Optional.empty();

        E result = null;
        for(E e : c){
            if(result == null || e.compareTo(result) > 0){
                result = Objects.requireNonNull(e);
            }
        }

        return Optional.of(result);
    }
}

