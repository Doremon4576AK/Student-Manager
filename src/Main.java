public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        manager.addStudent("S001", "Alice", 7.2);
        manager.addStudent("S002", "Bob", 8.5);
        manager.addStudent("S003", "Charlie", 5.4);
        manager.addStudent("S004", "David", 9.3);

        System.out.println("Before Sorting:");
        manager.displayStudents();

        manager.quickSort();

        System.out.println("\nAfter Sorting by Marks (Descending):");
        manager.displayStudents();
    }
}
