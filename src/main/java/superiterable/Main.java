package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Main {
    public static String toLetterGrade(Student s) {
        double d = s.getGpa();
        if (d > 3.5) return "A";
        if (d > 3.0) return "B";
        else return "C";
    }
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

//        System.out.println("----------------------");
//        ss
//                .forEach(stu -> System.out.println(stu));

        System.out.println("1) ----------------------");
        r.stream()
                .forEach(stu -> System.out.println(stu));

        System.out.println("2) ----------------------");
        r.stream()
                .filter(stu -> stu.getGpa() > 3)
                .forEach(stu -> System.out.println(stu));

        System.out.println("3) ----------------------");
        r.stream()
                .filter(stu -> stu.getGpa() < 3)
                .map(stu -> stu.getName() + " has grade " + stu.getGpa())
                .forEach(stu -> System.out.println(stu));

        System.out.println("4) ----------------------");
        r.stream()
                .filter(stu -> stu.getGpa() < 3)
                .map(stu -> stu.getName() + " has grade " + toLetterGrade(stu))
                .forEach(stu -> System.out.println(stu));

        System.out.println("5) ----------------------");
        r.stream()
                .flatMap(stu -> stu.getCourses().stream())
                .forEach(stu -> System.out.println(stu));

        System.out.println("6) ----------------------");
        r.stream()
                .flatMap(stu -> stu.getCourses().stream())
                .distinct()
                .forEach(stu -> System.out.println(stu));

        System.out.println("7) ----------------------");
        r.stream()
                .flatMap(stu -> stu.getCourses().stream())
                .distinct()
                .sorted()
                .forEach(stu -> System.out.println(stu));

        System.out.println("8) ----------------------");
        r.stream()
                .flatMap(stu -> stu.getCourses().stream().map(c -> stu.getName() + " takes " + c))
                .forEach(stu -> System.out.println(stu));

        System.out.println("9) ----------------------");
        /*OptionalDouble d = */r.stream()
                .mapToDouble(stu -> stu.getGpa())
                .average()/*;*/
        /*d*/   .ifPresent(v -> System.out.println("Average is " + v));

    }
}
