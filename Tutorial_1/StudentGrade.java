package Tutorial_1;
import java.util.Scanner;
public class StudentGrade
{
    public static void main(String[]args)
    {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your name:");
        String student_name= scanner.next();
        System.out.println("Enter your student id:");
        String student_ID = scanner.next();
        System.out.println("Enter your coding score:");
        String Grade= scanner.next();
        System.out.println("Hello "+student_name+" & "+student_ID);
        System.out.println("Coding Mark is"+Grade);


    }
}