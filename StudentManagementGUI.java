import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        // addSampleData(); //  
    }
    
    private void setupWindow() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 750);
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
        
        // South panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Operations", createStudentPanel());
        tabbedPane.addTab("Subject Operations", createSubjectPanel());
        tabbedPane.addTab("Display Options", createDisplayPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Welcome message
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
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(sectionLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(12);
        panel.add(idField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(12);
        panel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Major:"), gbc);
        gbc.gridx = 1;
        majorField = new JTextField(12);
        panel.add(majorField, gbc);
        
        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setBackground(new Color(46, 204, 113));
        addStudentBtn.setForeground(Color.WHITE);
        addStudentBtn.addActionListener(e -> addStudent());
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(addStudentBtn, gbc);
        
        // Remove Student Section
        gbc.gridy = 5;
        panel.add(new JSeparator(), gbc);
        
        gbc.gridy = 6;
        JLabel removeLabel = new JLabel("Remove Student");
        removeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(removeLabel, gbc);
        
        gbc.gridy = 7; gbc.gridwidth = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        JTextField removeIdField = new JTextField(12);
        panel.add(removeIdField, gbc);
        
        JButton removeStudentBtn = new JButton("Remove Student");
        removeStudentBtn.setBackground(new Color(231, 76, 60));
        removeStudentBtn.setForeground(Color.WHITE);
        removeStudentBtn.addActionListener(e -> removeStudent(removeIdField));
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        panel.add(removeStudentBtn, gbc);
        
        return panel;
    }
    
    private JPanel createSubjectPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Add Subject Section
        JLabel addLabel = new JLabel("Add Subject to Student");
        addLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(addLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        subjectIdField = new JTextField(12);
        panel.add(subjectIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Subject Name:"), gbc);
        gbc.gridx = 1;
        subjectNameField = new JTextField(12);
        panel.add(subjectNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Credit Hours:"), gbc);
        gbc.gridx = 1;
        hoursField = new JTextField(12);
        panel.add(hoursField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Grade (0-100):"), gbc);
        gbc.gridx = 1;
        gradeField = new JTextField(12);
        panel.add(gradeField, gbc);
        
        JButton addSubjectBtn = new JButton("Add Subject");
        addSubjectBtn.setBackground(new Color(52, 152, 219));
        addSubjectBtn.setForeground(Color.WHITE);
        addSubjectBtn.addActionListener(e -> addSubject());
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(addSubjectBtn, gbc);
        
        // ==================== Remove Subject Section ====================
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);
        
        gbc.gridy = 7;
        JLabel removeLabel = new JLabel("Remove Subject from Student");
        removeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(removeLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 8;
        panel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        JTextField removeSubStudentId = new JTextField(12);
        panel.add(removeSubStudentId, gbc);
        
        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Subject Name:"), gbc);
        gbc.gridx = 1;
        JTextField removeSubNameField = new JTextField(12);
        panel.add(removeSubNameField, gbc);
        
        JButton removeSubjectBtn = new JButton("Remove Subject");
        removeSubjectBtn.setBackground(new Color(231, 76, 60));
        removeSubjectBtn.setForeground(Color.WHITE);
        removeSubjectBtn.addActionListener(e -> 
            removeSubjectFromStudent(removeSubStudentId, removeSubNameField));
        
        gbc.gridx = 0; gbc.gridy = 10; gbc.gridwidth = 2;
        panel.add(removeSubjectBtn, gbc);
        
        return panel;
    }
    
        private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JButton displayStudentBtn = new JButton("Display Student Info");
        displayStudentBtn.setBackground(new Color(155, 89, 182));
        displayStudentBtn.setForeground(Color.WHITE);
        displayStudentBtn.addActionListener(e -> displayStudent());
        
        JButton displaySubjectsBtn = new JButton("Display Student Subjects");   // ← الزر الجديد
        displaySubjectsBtn.setBackground(new Color(26, 188, 156));
        displaySubjectsBtn.setForeground(Color.WHITE);
        displaySubjectsBtn.addActionListener(e -> displayStudentSubjects());
        
        JButton calcGpaBtn = new JButton("Calculate GPA");
        calcGpaBtn.setBackground(new Color(52, 152, 219));
        calcGpaBtn.setForeground(Color.WHITE);
        calcGpaBtn.addActionListener(e -> calculateGPA());
        
        JButton displayAllBtn = new JButton("Display All Students");
        displayAllBtn.setBackground(new Color(39, 174, 96));
        displayAllBtn.setForeground(Color.WHITE);
        displayAllBtn.addActionListener(e -> displayAllStudents());
        
        JButton highestGpaBtn = new JButton("Highest GPA Student");
        highestGpaBtn.setBackground(new Color(241, 196, 15));
        highestGpaBtn.setForeground(Color.BLACK);
        highestGpaBtn.addActionListener(e -> showHighestGPA());
        
        JButton convertGradeBtn = new JButton("Convert Grade to Letter");
        convertGradeBtn.setBackground(new Color(230, 126, 34));
        convertGradeBtn.setForeground(Color.WHITE);
        convertGradeBtn.addActionListener(e -> convertGrade());
        
        panel.add(displayStudentBtn);
        panel.add(displaySubjectsBtn);        // ← added
        panel.add(calcGpaBtn);
        panel.add(displayAllBtn);
        panel.add(highestGpaBtn);
        panel.add(convertGradeBtn);
        
        return panel;
    }
    
    // ====================== Action Methods ======================
    
    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String major = majorField.getText().trim();
            
            if (name.isEmpty() || major.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Major cannot be empty!");
                return;
            }
            
            if (system.addStudent(id, name, major)) {
                displayArea.append("✅ Student added successfully: " + name + " (ID: " + id + ")\n");
                clearStudentInputs();
            } else {
                JOptionPane.showMessageDialog(this, "Student ID already exists!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
        }
    }
    
    private void addSubject() {
        try {
            int studentId = Integer.parseInt(subjectIdField.getText().trim());
            String subjectName = subjectNameField.getText().trim();
            int creditHours = Integer.parseInt(hoursField.getText().trim());
            double grade = Double.parseDouble(gradeField.getText().trim());
            
            if (subjectName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Subject name cannot be empty!");
                return;
            }
            
            if (system.addSubjectToStudent(studentId, subjectName, creditHours, grade)) {
                displayArea.append("✅ Subject added: " + subjectName + " to student ID " + studentId + "\n");
                clearSubjectInputs();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found or invalid grade!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }
    
private void removeSubjectFromStudent(JTextField idField, JTextField nameField) {
    try {
        int studentId = Integer.parseInt(idField.getText().trim());
        String subjectName = nameField.getText().trim();

        if (subjectName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject name cannot be empty!", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (system.removeSubjectFromStudent(studentId, subjectName)) {
            displayArea.append("✅ Subject '" + subjectName + "' removed successfully from student ID " 
                             + studentId + "\n");
            idField.setText("");
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to remove subject!\n" +
                "• Make sure the student exists\n" +
                "• Make sure the subject name is correct", 
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid numeric Student ID!", 
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void removeStudent(JTextField removeIdField) {
        try {
            int id = Integer.parseInt(removeIdField.getText().trim());
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
                int id = Integer.parseInt(idStr.trim());
                Student student = system.findStudentById(id);
                if (student != null) {
                    displayArea.append(student.getStudentInfo() + "\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID!");
            }
        }
    }
        private void displayStudentSubjects() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID to display his subjects:");
        if (idStr == null || idStr.trim().isEmpty()) return;
        
        try {
            int id = Integer.parseInt(idStr.trim());
            String result = system.displayStudentSubjects(id);
            displayArea.append(result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric Student ID!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateGPA() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Student student = system.findStudentById(id);
                if (student != null) {
                    double gpa = student.calculateGPA();
                    displayArea.append(String.format("📊 %s (ID: %d) - GPA: %.2f\n", 
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
        displayArea.append("====================================\n");
        displayArea.append(system.displayAllStudents());
        displayArea.append("====================================\n\n");
    }
    
    private void showHighestGPA() {
        Student highest = system.getStudentWithHighestGPA();
        if (highest != null) {
            displayArea.append("🏆 Highest GPA Student:\n");
            displayArea.append(String.format("   %s - GPA: %.2f\n\n", 
                              highest.getSimpleInfo(), highest.calculateGPA()));
        } else {
            displayArea.append("No students in the system!\n");
        }
    }
    
    private void convertGrade() {
        String gradeStr = JOptionPane.showInputDialog(this, "Enter numeric grade (0-100):");
        if (gradeStr != null && !gradeStr.trim().isEmpty()) {
            try {
                double grade = Double.parseDouble(gradeStr.trim());
                if (grade < 0 || grade > 100) {
                    JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100!");
                    return;
                }
                
                String letter = grade >= 90 ? "A" : grade >= 80 ? "B" : 
                               grade >= 70 ? "C" : grade >= 60 ? "D" : "F";
                
                displayArea.append(String.format("📝 Grade %.1f → Letter Grade: %s\n", grade, letter));
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