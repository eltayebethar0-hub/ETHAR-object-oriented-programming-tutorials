package Tutorial_6;
public class Main {
    public static void main(String[] args) {
        // Create a new Lecturer object with the data from the expected output
        Lecturer lecturer = new Lecturer(
                "L100",
                "Dr Ahmad",
                "Java Programming",
                "Faculty of Information Technology"
        );

        // Call the methods to print the information
        lecturer.displayInfo();     // Inherited from Employee class
        lecturer.displaySubject();  // From Lecturer class
    }
}