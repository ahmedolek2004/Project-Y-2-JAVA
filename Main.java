import java.util.Scanner;

public class Main {
    private static StudentManagementSystem system = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Add sample data
        // system.addStudent(101, "Ahmed Ali", "Computer Science");
        // system.addStudent(102, "Sara Mohamed", "Software Engineering");
        // system.addStudent(103, "Omar Khaled", "Information Technology");
        
        // system.addSubjectToStudent(101, "Java Programming", 3, 92);
        // system.addSubjectToStudent(101, "Database Systems", 3, 85);
        
        int choice;
        do {
            showMenu();
            choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: addStudent(); break;
                case 2: addSubject(); break;
                case 3: displayStudent(); break;
                case 4: calculateGPA(); break;
                case 5: displayAllStudents(); break;
                case 6: removeStudent(); break;
                case 7: removeSubject(); break;
                case 8: showHighestGPA(); break;
                case 9: convertGrade(); break;
                case 10: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 10);
    }
    
    private static void showMenu() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("     Student Management System");
        System.out.println("═══════════════════════════════════════");
        System.out.println("1. Add Student");
        System.out.println("2. Add Subject to Student");
        System.out.println("3. Display Student Information");
        System.out.println("4. Calculate Student GPA");
        System.out.println("5. Display All Students");
        System.out.println("6. Remove Student");
        System.out.println("7. Remove Subject");
        System.out.println("8. Show student with highest GPA");
        System.out.println("9. Convert numeric grade to letter grade");
        System.out.println("10. Exit");
        System.out.println("═══════════════════════════════════════");
    }
    
    private static void addStudent() {
        int id = getIntInput("Enter Student ID: ");
        String name = getStringInput("Enter Name: ");
        String major = getStringInput("Enter Major: ");
        
        if (system.addStudent(id, name, major)) {
            System.out.println("✅ Student added successfully!");
        } else {
            System.out.println("❌ Student ID already exists or invalid input!");
        }
    }
    
    private static void addSubject() {
        int id = getIntInput("Enter Student ID: ");
        String subjectName = getStringInput("Enter Subject Name: ");
        int hours = getIntInput("Enter Credit Hours: ");
        double grade = getDoubleInput("Enter Grade: ");
        
        if (system.addSubjectToStudent(id, subjectName, hours, grade)) {
            System.out.println("✅ Subject added successfully!");
        } else {
            System.out.println("❌ Student not found or invalid grade!");
        }
    }
    
    private static void displayStudent() {
        int id = getIntInput("Enter Student ID: ");
        Student student = system.findStudentById(id);
        if (student != null) {
            System.out.print(student.getStudentInfo());
        } else {
            System.out.println("❌ Student not found!");
        }
    }
    
    private static void calculateGPA() {
        int id = getIntInput("Enter Student ID: ");
        Student student = system.findStudentById(id);
        if (student != null) {
            System.out.printf("📊 Student: %s - GPA: %.2f\n", student.getName(), student.calculateGPA());
        } else {
            System.out.println("❌ Student not found!");
        }
    }
    
    private static void displayAllStudents() {
        System.out.print(system.displayAllStudents());
    }
    
    private static void removeStudent() {
        int id = getIntInput("Enter Student ID to remove: ");
        if (system.removeStudent(id)) {
            System.out.println("✅ Student removed successfully!");
        } else {
            System.out.println("❌ Student not found!");
        }
    }
    
        private static void removeSubject() {
        int id = getIntInput("Enter Student ID: ");
        Student student = system.findStudentById(id);
        if (student == null) {
            System.out.println("❌ Student not found!");
            return;
        }
        
        // عرض المواد أولاً
        System.out.println("\nSubjects for student " + student.getName() + ":");
        if (student.getSubjects().isEmpty()) {
            System.out.println("No subjects found!");
            return;
        }
        
        for (int i = 0; i < student.getSubjects().size(); i++) {
            System.out.println((i + 1) + ". " + student.getSubjects().get(i));
        }
        
        scanner.nextLine(); // تنظيف الـ buffer
        System.out.print("Enter Subject Name to remove: ");
        String subjectName = scanner.nextLine().trim();
        
        if (system.removeSubjectFromStudent(id, subjectName)) {
            System.out.println("✅ Subject '" + subjectName + "' removed successfully!");
        } else {
            System.out.println("❌ Failed to remove subject! Make sure the name is correct.");
        }
    }
    
    private static void showHighestGPA() {
        Student highest = system.getStudentWithHighestGPA();
        if (highest != null) {
            System.out.printf("🏆 Student with Highest GPA: %s - GPA: %.2f\n", 
                            highest.getName(), highest.calculateGPA());
        } else {
            System.out.println("No students in the system!");
        }
    }
    
    private static void convertGrade() {
        double grade = getDoubleInput("Enter numeric grade: ");
        if (grade < 0 || grade > 100) {
            System.out.println("❌ Grade must be between 0 and 100!");
            return;
        }
        
        String letter;
        if (grade >= 90) letter = "A";
        else if (grade >= 80) letter = "B";
        else if (grade >= 70) letter = "C";
        else if (grade >= 60) letter = "D";
        else letter = "F";
        
        System.out.printf("📝 Grade %.2f → Letter Grade: %s\n", grade, letter);
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }
    
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // consume newline
        return scanner.nextLine().trim();
    }
}