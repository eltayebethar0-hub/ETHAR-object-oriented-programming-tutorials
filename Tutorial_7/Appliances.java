package Tutorial_7;
public abstract class Appliances {

    protected String brand;

    public Appliances(String brand) {
        this.brand = brand;
    }

    public void displayBrand() {
        System.out.println("Brand : " + this.brand);
    }

    public void turnOn() {
        System.out.println("Power ON");
    }

    public void turnOff() {
        System.out.println("Power OFF");
    }

    // Abstract method to be implemented by child classes
    public abstract void operate(); 
}