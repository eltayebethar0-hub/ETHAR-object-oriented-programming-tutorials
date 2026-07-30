package Tutorial_7;
public class WashingMachine extends Appliances {

    public WashingMachine(String brand) {
        super(brand);
    }

    @Override
    public void operate() {
        System.out.println("Washing clothes...");
    }
}