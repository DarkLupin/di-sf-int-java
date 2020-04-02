package morestream;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalResults1 {
    public static String toLetterGrade(Student s) {
        double d = s.getGpa();
        if (d > 3.5) return "A";
        if (d > 3.0) return "B";
        else return "C";
    }

    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Freddy", 3.5, "Math", "Physics"),
                Student.ofNameGpaCourses("Albert", 3.3, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        Map<String, List<Student>> results = roster.stream()
                .collect(Collectors.groupingBy(s -> toLetterGrade(s)));

        results.entrySet().stream()
                .forEach(e -> System.out.println(
                        "Students with grade " + e.getKey() + " are " + e.getValue()));

        roster.stream()
                .collect(Collectors.groupingBy(s -> toLetterGrade(s),
                        Collectors.counting()))

        .entrySet().stream()
                .forEach(e -> System.out.println(
                        e.getValue() + " students have grade " + e.getKey()));

    }
}
