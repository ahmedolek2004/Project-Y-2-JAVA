
import java.util.ArrayList;

/**
 * Represents a student in the system
 */
public class Student {

    private int studentId;
    private String name;
    private String major;
    private ArrayList<Subject> subjects;

    // Constructor
    public Student(int studentId, String name, String major) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.subjects = new ArrayList<>();
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // Add a subject to the student
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    // Remove a subject by index
    public void removeSubject(int index) {
        if (index >= 0 && index < subjects.size()) {
            subjects.remove(index);
        }
    }

    // Calculate GPA using the formula: Σ(grade × creditHours) / Σ(creditHours)
    public double calculateGPA() {
        if (subjects.isEmpty()) {
            return 0.0;
        }

        double totalGradePoints = 0;
        int totalCreditHours = 0;

        for (Subject subject : subjects) {
            totalGradePoints += subject.getGrade() * subject.getCreditHours();
            totalCreditHours += subject.getCreditHours();
        }

        if (totalCreditHours == 0) {
            return 0.0;
        }

        return totalGradePoints / totalCreditHours;
    }

    // Display student information as String
    public String getStudentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("═══════════════════════════════════════\n");
        info.append("Student ID: ").append(studentId).append("\n");
        info.append("Name: ").append(name).append("\n");
        info.append("Major: ").append(major).append("\n");
        info.append("Subjects:\n");

        if (subjects.isEmpty()) {
            info.append("  No subjects enrolled\n");
        } else {
            for (int i = 0; i < subjects.size(); i++) {
                info.append("  ").append(i + 1).append(". ").append(subjects.get(i).toString()).append("\n");
            }
            info.append(String.format("GPA: %.2f\n", calculateGPA()));
        }

        info.append("═══════════════════════════════════════\n");
        return info.toString();
    }

    // Simple display for list
    public String getSimpleInfo() {
        return name + " (ID: " + studentId + ") - Major: " + major;
    }

    @Override
    public String toString() {
        return name + " (ID: " + studentId + ")";
    }
}
