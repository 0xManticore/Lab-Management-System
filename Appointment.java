public class Appointment implements Cost{

    // attributes
    private static int appointmentCount = 1000;
    private int appointNo;
    private String appointDate;
    private Test testType;

    // default constructor
    Appointment() {
        appointNo = appointmentCount;
        appointDate = "";
        appointmentCount++;
    }

    // 3-args constructor
    Appointment(String d, Test t) {
        appointNo = appointmentCount;
        appointDate = d;
        testType = t;
        appointmentCount++;
    }

    // getCost implementation from interface cost
    public double getCost() {
        return testType.getTestCost();
    }

    // display method
    public void displayAppointment() {
        System.out.println("Appointment No: " + appointNo);
        System.out.println("Appointment Date: " + appointDate);
        System.out.println("Test Type: " + testType);
        System.out.println("Test Cost: " + testType.getTestCost());
        System.out.println("");
    }
}
