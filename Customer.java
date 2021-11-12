abstract class Customer {

    // attributes
    protected String name;
    protected char gender;
    protected int age;
    protected Address address;
    protected String phone;
    protected Appointment appointment;
    protected Result result;

    // default constructor
    Customer() {
        name = "";
        phone = "";
        address = new Address();
    }

    // 5-args constructor
    Customer(String n, char g, String p, int a, Address d) {
        name = n;
        gender = g;
        phone = p;
        age = a;
        address = d;
    }

    // Appointment accessor
    public Appointment getAppointment() {
        return appointment;
    }

    // result accessor
    public Result getResult() {
        return result;
    }

    // Appointment mutator
    public void bookAppointment(Appointment a) {
        appointment = a;
    }

    // Result mutator
    public void setResult(Result r) {
        result = r;
    }
    
    // abstract display method to be implemented in subclasses
    public abstract void display();
}
