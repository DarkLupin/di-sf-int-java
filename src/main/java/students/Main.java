package students;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        Student fred = new Student("Fred", 3.2, "Math", "Physics");
        Student fred = Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics");
        Student jim = Student.builder()
                .name("Jim")
                .gpa(2.6)
                .course("Art")
                .course("History")
                .build();
        System.out.println("Fred: " + fred);
        System.out.println("Jim: " + jim);

        // HashSet elements must implement hashcode and equals properly!
        Set<String> names = new HashSet<>(Arrays.asList("Albert", "Zebedee", "Morris", "William"));
        for (String s : names) {
            System.out.println("> " + s);
        }

        System.out.println("Set contains Fred? " + names.contains("Fred"));
        System.out.println("Set contains Albert? " + names.contains("Albert"));

        Set<StringBuilder> names2 = new HashSet<>(
                Arrays.asList(new StringBuilder("Albert"), new StringBuilder("Zebedee"),
                        new StringBuilder("Morris"), new StringBuilder("William")));
        System.out.println("Set 2 contains Fred? " + names2.contains(new StringBuilder("Fred")));
        System.out.println("Set 2 contains Albert? " + names2.contains(new StringBuilder("Albert")));

        StringBuilder n1 = new StringBuilder("Fred");
        StringBuilder n2 = new StringBuilder("Fred");
        System.out.println("n1.equals(n2) "+ (n1.equals(n2)));
    }

    public boolean isSmart(Student s) {
        return s.getGpa() > 3;
    }
}
