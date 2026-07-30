package Tutorial_7;
public class Main {

    public static void main(String[] args) {
        
        // Instantiate the two appliances using Polymorphism
        Appliances app1 = new WashingMachine("LG");
        Appliances app2 = new Refrigerator("Panasonic");

        // Execute methods for the first appliance (LG Washing Machine)
        app1.displayBrand();
        app1.turnOn();
        app1.operate();
        app1.turnOff();

        System.out.println();

        // Execute methods for the second appliance (Panasonic Refrigerator)
        app2.displayBrand();
        app2.turnOn();
        app2.operate();
        app2.turnOff();
    }
}