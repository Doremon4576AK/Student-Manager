import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.*;

public class StudentManagement extends JFrame {
    private JTextField txtID, txtName, txtMajor, txtSearch;
    private JButton btnAdd, btnDelete, btnShow, btnSort, btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    private java.util.List<Student> studentList = new ArrayList<>();

    public StudentManagement() {
        setTitle("Student Management");
        setSize(700, 500);
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

        txtMajor = new JTextField();
        txtMajor.setBounds(100, 100, 150, 25);
        add(txtMajor);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(270, 20, 100, 25);
        add(btnAdd);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(270, 60, 100, 25);
        add(btnDelete);

        btnShow = new JButton("Show All");
        btnShow.setBounds(270, 100, 100, 25);
        add(btnShow);

        btnSort = new JButton("Sort");
        btnSort.setBounds(380, 20, 100, 25);
        add(btnSort);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(380, 60, 100, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(450, 60, 140, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(600, 60, 80, 25);
        add(btnSearch);

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Full Name", "Major"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 650, 290);
        add(scrollPane);

        // Events
        btnAdd.addActionListener(e -> addStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnShow.addActionListener(e -> showAllStudents());
        btnSort.addActionListener(e -> sortStudentsByID());
        btnSearch.addActionListener(e -> searchStudents());
    }

    private void addStudent() {
        String id = txtID.getText().trim();
        String name = txtName.getText().trim();
        String major = txtMajor.getText().trim();

        if (id.isEmpty() || name.isEmpty() || major.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields.");
            return;
        }

        studentList.add(new Student(id, name, major));
        tableModel.addRow(new Object[]{id, name, major});

        txtID.setText("");
        txtName.setText("");
        txtMajor.setText("");
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
            tableModel.addRow(new Object[]{s.id, s.name, s.major});
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
                tableModel.addRow(new Object[]{s.id, s.name, s.major});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagement().setVisible(true);
        });
    }

    // Student class
    class Student {
        String id, name, major;

        public Student(String id, String name, String major) {
            this.id = id;
            this.name = name;
            this.major = major;
        }
    }
}
