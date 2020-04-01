package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student { // All constructors private also prevents subclassing...
    private String name;
    private double gpa; // float likely only useful for large arrays
    List<String> courses;

    public Student(String name, double gpa, String ... courses) {
        // courses IS an array of String, BUT compiler allows a, b, c style of invocation
        // compiler builds the array.
        // BUT caller is permitted to invoke this and provide the array directly...
        // IF the caller retains that array, they can mutate the underlying array
        // and this WILL mutate the elements of the list.
        // (Java 9 gives List.of(...) which is truly immutable
        if (gpa < 0 || gpa > 4) throw new IllegalArgumentException("Bad GPA");
        this.name = name;
        this.gpa = gpa;
        this.courses =  Arrays.asList(courses); // "Structurally immutable" list
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }
//
//    public Student setGpa(double gpa) {
//        // verify gpa!! or let constructor do it!!!
////        return new Student(this.name, gpa, this.courses);
//    }

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses); // Breaks Liskov substitution.
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }
}
