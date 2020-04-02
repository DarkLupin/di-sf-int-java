package morestream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class BehaviorFactories {
    public static Predicate<String> longStringPred(final int threshold) {
//        threshold++;
        return s -> s.length() > threshold; // "closure"
    }

    public static <T> Predicate<T> negate(Predicate<T> op) {
        return t -> {
            return !op.test(t);
        };
    }

    public static <T> Predicate<T> and(Predicate<T> op1, Predicate<T> op2) {
        return t -> {
            return op1.test(t) && op2.test(t);
        };
    }

    public static void main(String[] args) {
        Predicate<String> ps = s -> s.length() > 3;
        Predicate<String> notPs = ps.negate();

        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
        names.stream()
                .filter(negate(longStringPred(3)))
                .forEach(s -> System.out.println(s));
    }
}
