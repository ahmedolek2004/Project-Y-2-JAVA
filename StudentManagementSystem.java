import java.util.ArrayList;

/**
 * Manages all students in the system
 */
public class StudentManagementSystem {
    private ArrayList<Student> students;
    
    // Constructor
    public StudentManagementSystem() {
        students = new ArrayList<>();
    }
    
    // Add a new student
    public boolean addStudent(int id, String name, String major) {
        // Check if student already exists
        if (findStudentById(id) != null) {
            return false;
        }
        
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        Student student = new Student(id, name, major);
        students.add(student);
        return true;
    }
    
    // Find student by ID (public access)
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }
    
    // Add subject to a student
    public boolean addSubjectToStudent(int studentId, String subjectName, int creditHours, double grade) {
        // Validate grade
        if (grade < 0 || grade > 100) {
            return false;
        }
        
        // Validate credit hours
        if (creditHours <= 0) {
            return false;
        }
        
        Student student = findStudentById(studentId);
        if (student == null) {
            return false;
        }
        
        Subject subject = new Subject(subjectName, creditHours, grade);
        student.addSubject(subject);
        return true;
    }
    
    // Remove a student
    public boolean removeStudent(int studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
    
    // Remove a subject from a student
    public boolean removeSubjectFromStudent(int studentId, int subjectIndex) {
        Student student = findStudentById(studentId);
        if (student == null) {
            return false;
        }
        
        ArrayList<Subject> subjects = student.getSubjects();
        if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
            student.removeSubject(subjectIndex);
            return true;
        }
        return false;
    }
    
    // Get student GPA
    public double getStudentGPA(int studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            return student.calculateGPA();
        }
        return -1;
    }
    
    // Get student with highest GPA
    public Student getStudentWithHighestGPA() {
        if (students.isEmpty()) {
            return null;
        }
        
        Student highestGPAStudent = students.get(0);
        double highestGPA = highestGPAStudent.calculateGPA();
        
        for (Student student : students) {
            double currentGPA = student.calculateGPA();
            if (currentGPA > highestGPA) {
                highestGPA = currentGPA;
                highestGPAStudent = student;
            }
        }
        
        return highestGPAStudent;
    }
    
    // Get all students
    public ArrayList<Student> getAllStudents() {
        return students;
    }
    
    // Display all students as String
    public String displayAllStudents() {
        if (students.isEmpty()) {
            return "No students in the system.\n";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("Students in the system:\n");
        for (int i = 0; i < students.size(); i++) {
            sb.append(i + 1).append(". ").append(students.get(i).getSimpleInfo()).append("\n");
        }
        sb.append("═══════════════════════════════════════\n");
        return sb.toString();
    }
    
    // Get number of students
    public int getStudentCount() {
        return students.size();
    }
}