package Tutorial_7;
public class Refrigerator extends Appliances {

    public Refrigerator(String brand) {
        super(brand);// Calls the parent class constructor
    }

    @Override
    public void operate() {
        System.out.println("Store food & beverages...");
    }
}