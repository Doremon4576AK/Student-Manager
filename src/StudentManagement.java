import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;

public class StudentManagement extends JFrame {
    private JTextField txtID, txtName, txtSearch, txtScore1, txtScore2, txtScore3;
    private JComboBox<String> cbMajor;
    private JButton btnAdd, btnDelete, btnShow, btnSort, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblScore1, lblScore2, lblScore3;

    private java.util.List<Student> studentList = new ArrayList<>();

    public StudentManagement() {
        setTitle("Student Management");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblID = new JLabel("Student ID:");
        lblID.setBounds(20, 20, 100, 25);
        add(lblID);

        txtID = new JTextField();
        txtID.setBounds(100, 20, 150, 25);
        add(txtID);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(20, 60, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(100, 60, 150, 25);
        add(txtName);

        JLabel lblMajor = new JLabel("Major:");
        lblMajor.setBounds(20, 100, 100, 25);
        add(lblMajor);

        cbMajor = new JComboBox<>(new String[]{"IT", "Marketing", "Design"});
        cbMajor.setBounds(100, 100, 150, 25);
        add(cbMajor);

        lblScore1 = new JLabel("Score 1:");
        lblScore1.setBounds(300, 20, 100, 25);
        add(lblScore1);

        txtScore1 = new JTextField();
        txtScore1.setBounds(400, 20, 100, 25);
        add(txtScore1);

        lblScore2 = new JLabel("Score 2:");
        lblScore2.setBounds(300, 60, 100, 25);
        add(lblScore2);

        txtScore2 = new JTextField();
        txtScore2.setBounds(400, 60, 100, 25);
        add(txtScore2);

        lblScore3 = new JLabel("Score 3:");
        lblScore3.setBounds(300, 100, 100, 25);
        add(lblScore3);

        txtScore3 = new JTextField();
        txtScore3.setBounds(400, 100, 100, 25);
        add(txtScore3);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(520, 20, 100, 25);
        add(btnAdd);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(520, 60, 100, 25);
        add(btnDelete);

        btnShow = new JButton("Show All");
        btnShow.setBounds(520, 100, 100, 25);
        add(btnShow);

        btnSort = new JButton("Sort");
        btnSort.setBounds(630, 20, 100, 25);
        add(btnSort);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(630, 60, 100, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(630, 85, 100, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(630, 115, 100, 25);
        add(btnSearch);

        tableModel = new DefaultTableModel(new String[]{
                "Student ID", "Full Name", "Major", "Subject 1", "Subject 2", "Subject 3"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 160, 710, 330);
        add(scrollPane);

        // Sự kiện
        cbMajor.addActionListener(e -> updateSubjectLabels());
        btnAdd.addActionListener(e -> addStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnShow.addActionListener(e -> showAllStudents());
        btnSort.addActionListener(e -> sortStudentsByID());
        btnSearch.addActionListener(e -> searchStudents());

        // Cập nhật tên môn ban đầu
        updateSubjectLabels();
    }

    private void updateSubjectLabels() {
        String major = (String) cbMajor.getSelectedItem();
        switch (major) {
            case "IT":
                lblScore1.setText("Programming:");
                lblScore2.setText("Database:");
                lblScore3.setText("Networking:");
                break;
            case "Marketing":
                lblScore1.setText("SEO:");
                lblScore2.setText("Branding:");
                lblScore3.setText("Communication:");
                break;
            case "Design":
                lblScore1.setText("Photoshop:");
                lblScore2.setText("Illustrator:");
                lblScore3.setText("UI/UX:");
                break;
        }
    }

    private void addStudent() {
        String id = txtID.getText().trim();
        String name = txtName.getText().trim();
        String major = (String) cbMajor.getSelectedItem();
        String score1 = txtScore1.getText().trim();
        String score2 = txtScore2.getText().trim();
        String score3 = txtScore3.getText().trim();

        if (id.isEmpty() || name.isEmpty() || score1.isEmpty() || score2.isEmpty() || score3.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.");
            return;
        }

        try {
            double s1 = Double.parseDouble(score1);
            double s2 = Double.parseDouble(score2);
            double s3 = Double.parseDouble(score3);
            studentList.add(new Student(id, name, major, s1, s2, s3));
            tableModel.addRow(new Object[]{id, name, major, s1, s2, s3});
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Scores must be numeric.");
        }

        txtID.setText("");
        txtName.setText("");
        txtScore1.setText("");
        txtScore2.setText("");
        txtScore3.setText("");
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            studentList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    private void showAllStudents() {
        tableModel.setRowCount(0);
        for (Student s : studentList) {
            tableModel.addRow(new Object[]{s.id, s.name, s.major, s.score1, s.score2, s.score3});
        }
    }

    private void sortStudentsByID() {
        studentList.sort(Comparator.comparing(s -> s.id));
        showAllStudents();
    }

    private void searchStudents() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0);

        for (Student s : studentList) {
            if (s.id.toLowerCase().contains(keyword) || s.name.toLowerCase().contains(keyword)) {
                tableModel.addRow(new Object[]{s.id, s.name, s.major, s.score1, s.score2, s.score3});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagement().setVisible(true));
    }

    class Student {
        String id, name, major;
        double score1, score2, score3;

        public Student(String id, String name, String major, double score1, double score2, double score3) {
            this.id = id;
            this.name = name;
            this.major = major;
            this.score1 = score1;
            this.score2 = score2;
            this.score3 = score3;
        }
    }
}
