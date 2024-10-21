import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;

    // Constructor
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter and Setter methods for the Student class
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // To display student details
    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

public class StudentManagementSystem {
    private ArrayList<Student> students;
    private Scanner scanner;

    // Constructor to initialize the student list and scanner
    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a new student
    public void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        
        students.add(new Student(id, name, age));
        System.out.println("Student added successfully.");
    }

    // Method to view all students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to update student details
    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        Student student = findStudentById(id);
        
        if (student != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new age: ");
            int newAge = scanner.nextInt();
            
            student.setName(newName);
            student.setAge(newAge);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Method to remove a student
    public void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int id = scanner.nextInt();
        Student student = findStudentById(id);
        
        if (student != null) {
            students.remove(student);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // Method to find a student by ID
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Method to display the main menu and handle user inputs
    public void run() {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
class StudentManagementSystems{
    // Main method to start the program
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}
}
