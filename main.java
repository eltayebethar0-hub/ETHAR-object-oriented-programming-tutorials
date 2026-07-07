public class main{
    public static void main(String[]args){
        Appliance app1 = new WashingMachine("LG");
app1.displayBrand();
app1.turnOn();
app1.operate();
app1.turnOff();

System.out.println();

Appliance app2 = new WashingMachine("Panasonic");
app2.displayBrand();
app2.turnOn();
app2.operate();
app2.turnOff();

    }
}