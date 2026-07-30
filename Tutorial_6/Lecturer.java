package Tutorial_6;
public class Lecturer extends Employee {
    private String subject;
    private String department; // Added for Activity 2

    // Constructor updated to include subject and department
    public Lecturer(String id, String name, String subject, String department) {
        super(id, name); // Calls the constructor of the parent class (Employee)
        this.subject = subject;
        this.department = department;
    }

    public void displaySubject() {
        System.out.println("Subject     : " + subject);
        System.out.println("Department  : " + department); // Added to match expected output
    }
}