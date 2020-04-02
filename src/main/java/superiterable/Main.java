package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Student> r = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        List<String> s = Arrays.asList("This", "is", "some", "words", "of", "varying", "lengths");

        SuperIterable<Student> roster = new SuperIterable<>(r);

//        for (Student stu : roster) {
//            System.out.println("> " + stu);
//        }

//        roster.showAll(stu -> System.out.println("Student with name " + stu.getName()));
        roster
                .map(stu -> stu.setGpa(stu.getGpa() + 0.1))
                .filter(stu -> stu.getGpa() > 3.1)
                .map(stu -> "Student called " + stu.getName() + " has grade " + stu.getGpa())
                .forEach(stu -> System.out.println(stu));

        System.out.println("----------------------");
        roster
                .flatMap(stu -> new SuperIterable(stu.getCourses()))
                .forEach(stu -> System.out.println(stu));

        System.out.println("----------------------");

        r.stream()
                .peek(stu -> System.out.println("first peek processing " + stu))
                .map(stu -> stu.setGpa(stu.getGpa() + 0.1))
                .filter(stu -> stu.getGpa() > 3.1)
                .peek(stu -> System.out.println("second peek processing " + stu))
                .map(stu -> "Student called " + stu.getName() + " has grade " + stu.getGpa())
                .forEach(stu -> System.out.println(stu));

        System.out.println("----------------------");
        Stream<Student> ss = r.stream();
        ss
                .flatMap(stu -> stu.getCourses().stream())
                .forEach(stu -> System.out.println(stu));

        System.out.println("----------------------");
        ss
                .forEach(stu -> System.out.println(stu));
    }
}
