package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//class VIPStudent extends Student {...}

public final class Student /*implements Comparable<Student>*/ { // All constructors private also prevents subclassing...
    private String name;
    private double gpa; // float likely only useful for large arrays
    List<String> courses; // Set logically better, but can be hard to use.

    public static class StudentBuilder {
        private Student self = new Student();
        {
            self.courses = new ArrayList<>();
        }
        public StudentBuilder name(String name) {
            self.name = name;
            return this;
        }
        public StudentBuilder gpa(double gpa) {
            self.gpa = gpa;
            return this;
        }
        public StudentBuilder course(String course) {
            self.courses.add(course);
            return this;
        }
        public Student build() {
            Student retVal = self;
            self = null;
            retVal.isValid();
            return retVal;
        }
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static Student ofNameGpaCourses(String name, double gpa, String ... courses) {
//        if (Math.random() > 0.5) {
            return new Student(name, gpa, courses);
//        } else {
//            return new VIPStudent(...)
//        }
    }
    private Student(String name, double gpa, String ... courses) {
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

    private Student() {}

    private Student(String name, double gpa, List<String> courses) {
        this.name = name;
        this.gpa = gpa;
        this.courses = courses; // not "safe for immutability"
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public Student setGpa(double gpa) {
        // verify gpa!! or let constructor do it!!!
        return new Student(this.name, gpa, this.courses);
    }

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses); // Breaks Liskov substitution.
    }

    public void isValid() {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Bad Name");
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
