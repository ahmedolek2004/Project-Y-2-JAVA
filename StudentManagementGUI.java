import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for Student Management System using Swing
 */
public class StudentManagementGUI extends JFrame {
    private StudentManagementSystem system;
    private JTextArea displayArea;
    private JTextField idField, nameField, majorField;
    private JTextField subjectIdField, subjectNameField, hoursField, gradeField;
    
    public StudentManagementGUI() {
        system = new StudentManagementSystem();
        setupWindow();
        addSampleData(); // Add some sample data for testing
    }
    
    private void addSampleData() {
        system.addStudent(101, "Ahmed Ali", "Computer Science");
        system.addStudent(102, "Sara Mohamed", "Software Engineering");
        system.addStudent(103, "Omar Khaled", "Information Technology");
        
        system.addSubjectToStudent(101, "Java Programming", 3, 92);
        system.addSubjectToStudent(101, "Database Systems", 3, 85);
        system.addSubjectToStudent(101, "Data Structures", 4, 90);
        
        system.addSubjectToStudent(102, "Python Programming", 3, 95);
        system.addSubjectToStudent(102, "Web Development", 3, 88);
    }
    
    private void setupWindow() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Student Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 33, 99));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Center panel with display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output Area"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // South panel with tabs for different operations
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Operations", createStudentPanel());
        tabbedPane.addTab("Subject Operations", createSubjectPanel());
        tabbedPane.addTab("Display Options", createDisplayPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Display welcome message
        displayArea.append("Welcome to Student Management System!\n\n");
        displayArea.append(system.displayAllStudents());
    }
    
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Add Student Section
        JLabel sectionLabel = new JLabel("Add New Student");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(sectionLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(10);
        panel.add(idField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(10);
        panel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Major:"), gbc);
        gbc.gridx = 1;
        majorField = new JTextField(10);
        panel.add(majorField, gbc);
        
        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setBackground(new Color(46, 204, 113));
        addStudentBtn.addActionListener(e -> addStudent());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(addStudentBtn, gbc);
        
        // Separator
        gbc.gridy = 5;
        panel.add(new JSeparator(), gbc);
        
        // Remove Student Section
        gbc.gridy = 6;
        JLabel removeLabel = new JLabel("Remove Student");
        removeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(removeLabel, gbc);
        
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        JTextField removeIdField = new JTextField(10);
        panel.add(removeIdField, gbc);
        
        JButton removeStudentBtn = new JButton("Remove Student");
        removeStudentBtn.setBackground(new Color(231, 76, 60));
        removeStudentBtn.addActionListener(e -> removeStudent(removeIdField));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(removeStudentBtn, gbc);
        
        return panel;
    }
    
    private JPanel createSubjectPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Add Subject Section
        JLabel sectionLabel = new JLabel("Add Subject to Student");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(sectionLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        subjectIdField = new JTextField(10);
        panel.add(subjectIdField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Subject Name:"), gbc);
        gbc.gridx = 1;
        subjectNameField = new JTextField(10);
        panel.add(subjectNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Credit Hours:"), gbc);
        gbc.gridx = 1;
        hoursField = new JTextField(10);
        panel.add(hoursField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Grade (0-100):"), gbc);
        gbc.gridx = 1;
        gradeField = new JTextField(10);
        panel.add(gradeField, gbc);
        
        JButton addSubjectBtn = new JButton("Add Subject");
        addSubjectBtn.setBackground(new Color(52, 152, 219));
        addSubjectBtn.addActionListener(e -> addSubject());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(addSubjectBtn, gbc);
        
        return panel;
    }
    
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JButton displayStudentBtn = new JButton("Display Student Info");
        displayStudentBtn.setBackground(new Color(155, 89, 182));
        displayStudentBtn.addActionListener(e -> displayStudent());
        
        JButton calcGpaBtn = new JButton("Calculate GPA");
        calcGpaBtn.setBackground(new Color(52, 152, 219));
        calcGpaBtn.addActionListener(e -> calculateGPA());
        
        JButton displayAllBtn = new JButton("Display All Students");
        displayAllBtn.setBackground(new Color(39, 174, 96));
        displayAllBtn.addActionListener(e -> displayAllStudents());
        
        JButton highestGpaBtn = new JButton("Highest GPA Student");
        highestGpaBtn.setBackground(new Color(241, 196, 15));
        highestGpaBtn.addActionListener(e -> showHighestGPA());
        
        JButton convertGradeBtn = new JButton("Convert Grade to Letter");
        convertGradeBtn.setBackground(new Color(230, 126, 34));
        convertGradeBtn.addActionListener(e -> convertGrade());
        
        panel.add(displayStudentBtn);
        panel.add(calcGpaBtn);
        panel.add(displayAllBtn);
        panel.add(highestGpaBtn);
        panel.add(convertGradeBtn);
        
        return panel;
    }
    
    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText().trim();
            String major = majorField.getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty!");
                return;
            }
            
            if (system.addStudent(id, name, major)) {
                displayArea.append("✅ Student added successfully: " + name + " (ID: " + id + ")\n");
                clearStudentInputs();
            } else {
                JOptionPane.showMessageDialog(this, "Student ID already exists or invalid input!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
        }
    }
    
    private void addSubject() {
        try {
            int studentId = Integer.parseInt(subjectIdField.getText());
            String subjectName = subjectNameField.getText().trim();
            int creditHours = Integer.parseInt(hoursField.getText());
            double grade = Double.parseDouble(gradeField.getText());
            
            if (subjectName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Subject name cannot be empty!");
                return;
            }
            
            if (system.addSubjectToStudent(studentId, subjectName, creditHours, grade)) {
                displayArea.append("✅ Subject added: " + subjectName + " to student ID " + studentId + "\n");
                clearSubjectInputs();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found or invalid grade (0-100)!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }
    
    private void removeStudent(JTextField removeIdField) {
        try {
            int id = Integer.parseInt(removeIdField.getText());
            Student student = system.findStudentById(id);
            if (student != null) {
                system.removeStudent(id);
                displayArea.append("❌ Student removed: " + student.getName() + " (ID: " + id + ")\n");
                removeIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
        }
    }
    
    private void displayStudent() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Student student = system.findStudentById(id);
                if (student != null) {
                    displayArea.append(student.getStudentInfo());
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
            }
        }
    }
    
    private void calculateGPA() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Student student = system.findStudentById(id);
                if (student != null) {
                    double gpa = student.calculateGPA();
                    displayArea.append(String.format("📊 Student: %s (ID: %d) - GPA: %.2f\n", 
                                        student.getName(), id, gpa));
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
            }
        }
    }
    
    private void displayAllStudents() {
        displayArea.append(system.displayAllStudents());
    }
    
    private void showHighestGPA() {
        Student highest = system.getStudentWithHighestGPA();
        if (highest != null) {
            displayArea.append("🏆 Student with Highest GPA:\n");
            displayArea.append(String.format("   %s - GPA: %.2f\n", 
                              highest.getSimpleInfo(), highest.calculateGPA()));
        } else {
            displayArea.append("No students in the system!\n");
        }
    }
    
    private void convertGrade() {
        String gradeStr = JOptionPane.showInputDialog(this, "Enter numeric grade (0-100):");
        if (gradeStr != null && !gradeStr.trim().isEmpty()) {
            try {
                double grade = Double.parseDouble(gradeStr);
                if (grade < 0 || grade > 100) {
                    JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100!");
                    return;
                }
                
                String letterGrade;
                if (grade >= 90) letterGrade = "A";
                else if (grade >= 80) letterGrade = "B";
                else if (grade >= 70) letterGrade = "C";
                else if (grade >= 60) letterGrade = "D";
                else letterGrade = "F";
                
                displayArea.append(String.format("📝 Grade %.2f → Letter Grade: %s\n", grade, letterGrade));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        }
    }
    
    private void clearStudentInputs() {
        idField.setText("");
        nameField.setText("");
        majorField.setText("");
    }
    
    private void clearSubjectInputs() {
        subjectIdField.setText("");
        subjectNameField.setText("");
        hoursField.setText("");
        gradeField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementGUI().setVisible(true);
        });
    }
}