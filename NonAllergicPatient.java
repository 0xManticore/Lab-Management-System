public class NonAllergicPatient extends Customer{

    // attributes
    String lastCheckDate;

    // default constructor
    NonAllergicPatient() {
        lastCheckDate = "";
    }

    // 6-args constructor
    NonAllergicPatient(String n, char g, String p, int a, Address d, String c) {
        super(n, g, p, a, d);
        lastCheckDate = c;
    }
    
    // display method
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phone);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address.getFullAddress());
        System.out.println("Last Check Date: " + lastCheckDate);
    }
}
