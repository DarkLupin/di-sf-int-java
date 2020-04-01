package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface StudentCriterion {
    boolean test(Student s);
}

class SmartCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getGpa() > 3;
    }
}

class EnthusiasticCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 2;
    }
}

public class Main2 {
    // Gang of four call "passing an object as an argument for the primary sake of the
    // BEHAVIOR it contains "Command" pattern.
    public static List<Student> getByCriterion(List<Student> roster, StudentCriterion crit) {
        List<Student> out = new ArrayList<>();
        for (Student s : roster) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

//    public static List<Student> getAllSmart(List<Student> roster, double threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : roster) {
//            if (s.getGpa() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }
//
//    public static List<Student> getEnthusiasic(List<Student> roster, int threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : roster) {
//            if (s.getCourses().size() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }

    public static void showAll(List<Student> roster) {
        for (Student s : roster) {
            System.out.println("> " + s);
        }
    }

    public static void main(String[] args) {

        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 2.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.9, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

//        showAll(getAllSmart(roster, 3.5));
        System.out.println("----- smart ------");
        showAll(getByCriterion(roster, new SmartCriterion()));
        System.out.println("----- enthusiastic ------");
        showAll(getByCriterion(roster, new EnthusiasticCriterion()));

        System.out.println("----- unenthusiastic ------");
        showAll(getByCriterion(roster, new StudentCriterion() {
            @Override
            public boolean test(Student s) {
                return s.getCourses().size() < 3;
            }
        }));

        System.out.println("----- not smart ------");
//        showAll(getByCriterion(roster, /*new StudentCriterion()*/ /*{*/
//            /*@Override
//            public boolean test*/(Student s) -> {
//                return s.getGpa() < 3;
//            }
//        /*}*/));

        showAll(getByCriterion(roster, (Student s) -> { return s.getGpa() < 3; } ));
        
        // ??? MUST BE an object of StudentCriterion type
        // Imagine ??? is "raw material" for an object, not the whole thing.
        // Compiler seek to create an instance of the right type from that raw material...
        // IF and ONLY IF
        // 1) target type MUST be an interface
        // 2) That interface MUST declare EXACTLY ONE abstract method
        // 3) YOU ONLY want to implement that ONE abstract method

    }
}
