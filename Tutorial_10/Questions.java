package Tutorial_10;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
    private final String questionText;
    private final String[] options;
    private final int correctOptionIndex;

    public Questions(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int index) {
        return index == correctOptionIndex;
    }

    public static List<Questions> getQuestionBank() {
        List<Questions> bank = new ArrayList<>();

        bank.add(new Questions(
            "Which keyword in Java is used to inherit a superclass?",
            new String[]{"implements", "extends", "inherits", "super"},
            1
        ));

        bank.add(new Questions(
            "Which OOP principle hides internal implementation details from the user?",
            new String[]{"Encapsulation", "Polymorphism", "Abstraction", "Inheritance"},
            2
        ));

        bank.add(new Questions(
            "What is the default value of an uninitialized instance reference variable?",
            new String[]{"0", "false", "null", "undefined"},
            2
        ));

        bank.add(new Questions(
            "Which modifier prevents a class from being subclassed?",
            new String[]{"static", "final", "abstract", "private"},
            1
        ));

        bank.add(new Questions(
            "Which collection guarantees unique elements and maintains insertion order?",
            new String[]{"HashSet", "TreeSet", "LinkedHashSet", "ArrayList"},
            2
        ));

        bank.add(new Questions(
            "What happens when a subclass defines a method with the same signature as a parent class method?",
            new String[]{"Method Overloading", "Method Overriding", "Method Hiding", "Compilation Error"},
            1
        ));

        bank.add(new Questions(
            "Which keyword is used to explicitly call a parent constructor?",
            new String[]{"this()", "parent()", "super()", "base()"},
            2
        ));

        bank.add(new Questions(
            "Which exception type is checked at compile-time in Java?",
            new String[]{"NullPointerException", "ArrayIndexOutOfBoundsException", "IOException", "ArithmeticException"},
            2
        ));

        Collections.shuffle(bank);
        return bank;
    }
}