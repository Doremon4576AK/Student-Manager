import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String id, String name, double marks) {
        students.add(new Student(id, name, marks));
    }

    public boolean editStudent(String id, String newName, Double newMarks) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                if (newName != null) s.setName(newName);
                if (newMarks != null) s.setMarks(newMarks);
                return true;
            }
        }
        return false;
    }

    public void deleteStudent(String id) {
        students.removeIf(s -> s.getStudentId().equals(id));
    }

    public List<Student> searchStudent(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(keyword) || s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public void quickSort() {
        quickSortRecursive(0, students.size() - 1);
    }

    private void quickSortRecursive(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSortRecursive(low, pi - 1);
            quickSortRecursive(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students.get(high).getMarks();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() >= pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }

    public void displayStudents() {
        System.out.println("ID\tName\tMarks\tRank");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
