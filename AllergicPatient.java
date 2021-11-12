public class AllergicPatient extends Customer{

    // attributes
    String lastCheckDate;
    String allergyType;

    // default constructor
    AllergicPatient() {
        lastCheckDate = "";
        allergyType = "";
    }

    // 7-args constructor
    AllergicPatient(String n, char g, String p, int a, Address d, String c, String t) {
        super(n, g, p, a, d);
        lastCheckDate = c;
        allergyType = t;
    }
    
    // display method
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phone);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address.getFullAddress());
        System.out.println("Last Check Date: " + lastCheckDate);
        System.out.println("Allergy Type: " + allergyType);
    }
}
