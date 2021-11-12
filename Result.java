public class Result {
    
    // attributes
    private String patientStatus;
    private String doctorNotes;

    // default constructor
    Result(){
        patientStatus = "";
        doctorNotes = "";
    }

    // 2-args constructor
    Result(String p, String d) {
        patientStatus = p;
        doctorNotes = d;
    }

    // display method
    public void displayResult(){
        System.out.println("Patient Status: " + patientStatus);
        System.out.println("Doctor Notes: " + doctorNotes);
    }
}
