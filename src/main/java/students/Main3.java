package students;

import java.util.*;

interface Criterion<E> {
    boolean test(E e);
}

public class Main3 {
    public static <T> List<T> getByCriterion(Iterable<T> roster, Criterion<T> crit) {
        List<T> out = new ArrayList<>();
        for (T s : roster) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

    public static <T> void showAll(List<T> roster) {
        for (T s : roster) {
            System.out.println("> " + s);
        }
    }

    public static void main(String[] args) {

        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        showAll(getByCriterion(roster, s -> { return s.getGpa() < 3; } ));

        List<String> longStrings = getByCriterion(
                Arrays.asList("This", "is", "some", "words", "of", "varying", "lengths"),
                string -> string.length() > 4
        );
        System.out.println(longStrings);
        System.out.println("----------------");
        showAll(getByCriterion(
                Arrays.asList("This", "is", "some", "words", "of", "varying", "lengths"),
                string -> string.length() < 6));
    }
}
