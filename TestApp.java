/*

    Saleh Mohamed | A19EC4058
    Anas Roubi | A19EC4047
    Majd Alali | A19EC4038

*/
import java.util.*;
import java.io.*;  

public class TestApp {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Appointment> appointments = new ArrayList<Appointment>();  

    public static void CreateNewCustomer() {
        System.out.println("---[1] Add New Customer---");

        // Input Handler 
        Scanner input = new Scanner(System.in);

        // Basic Customer Information
        String name, phone;
        char gender;
        int age;

        System.out.print("Enter your name: ");
        name = input.nextLine();

        do {
            System.out.print("Enter your gender (M/F): ");
            gender = Character.toUpperCase(input.next().charAt(0));
        } while (gender != 'M' && gender != 'F');

        input.nextLine();

        do {
            System.out.print("Enter your phone: ");
            phone = input.nextLine();
        } while (phone.length() < 10);


        do {
            try {
                System.out.print("Enter your age: ");
                age = input.nextInt();
                if (age < 12 || age > 100){
                    throw new Exception();
                }
            } catch (InputMismatchException e){
                System.out.println("Age shoud be a number..");
                input.next();
                age = 0;
            } catch (Exception e){
                age = 0;
            }
        } while (age == 0);

        // Address Information
        String state, city, street;
        int postcode;

        System.out.print("Enter your street: ");
        input.nextLine();
        street = input.nextLine();
        System.out.print("Enter your city: ");
        city = input.nextLine();
        System.out.print("Enter your state: ");
        state = input.nextLine();

        do {
            try {
                System.out.print("Enter your postcode: ");
                postcode = input.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Postcode shoud be a number..");
                input.next();
                postcode = 0;
            }
        } while (postcode == 0);

        Address address = new Address(street, city, state, postcode);

        // Allergy Status 
        char allergyStatus;
        String lastCheckDate;

        do {
            System.out.print("\nAre You Allergic (Y/N): ");
            allergyStatus = Character.toUpperCase(input.next().charAt(0));
        } while (allergyStatus != 'Y' && allergyStatus != 'N');
        
        System.out.print("Enter Last Check Date (DD/MM/YYYY): ");
        input.nextLine();
        lastCheckDate = input.nextLine();

        String allergyType = "";
        // Creating New Customer
        if(allergyStatus == 'Y') {

            System.out.print("Enter Your Allergy Type: ");
            allergyType = input.nextLine();
            AllergicPatient customer = new AllergicPatient(name, gender, phone, age, address, lastCheckDate, allergyType);
            customers.add(customer);
        } else if (allergyStatus == 'N') {
            NonAllergicPatient customer = new NonAllergicPatient(name, gender, phone, age, address, lastCheckDate);
            customers.add(customer);
        }


        // Creating patients file 
        String patientFileName = "patientFile.txt"; 

        try {  
            File f = new File(patientFileName);   
            f.createNewFile();
        } catch (IOException e) {}

        // Appending to the file
        try {  
            FileWriter patientFile = new FileWriter(patientFileName,true);
            patientFile.write(name + ", ");
            patientFile.write(gender + ", ");
            patientFile.write(phone + ", ");
            patientFile.write(age + ", ");
            patientFile.write("(" + address.getFullAddress() + "), ");
            patientFile.write(lastCheckDate + ", ");
            if(allergyStatus == 'Y') {
                patientFile.write(allergyType + "\n");
            } else {
                patientFile.write("NonAllergic\n");
            }
            
            patientFile.close();
        } catch (IOException e) {}


        System.out.println("The patient has been added, you will be redirected to the main menu...");
    }

    public static void DisplayPatients() {
        System.out.println("---[2] View All Patients---");

        // Printing Patients
        if (customers.size() > 0) System.out.println("\n----- Patients -----");
        for (int i = 0 ; i < customers.size(); i++){
            System.out.printf("Patient #%d\n", i+1);
            customers.get(i).display();
            if(customers.get(i).getAppointment() != null) {
                System.out.println(" --- Appointment --- ");
                customers.get(i).getAppointment().displayAppointment();
            }
            
            if(customers.get(i).getResult() != null) {
                System.out.println(" --- Result --- ");
                customers.get(i).getResult().displayResult();
            }
            System.out.println("");
        }

    }

    public static void MakeNewAppointment() {
        System.out.println("---[3] Make Appointment---");

        // Input Handler 
        Scanner input = new Scanner(System.in);
        int customerNumber;
        
        do {
            try {
                System.out.print("Enter customer number: ");
                customerNumber = input.nextInt();
                if (customerNumber > customers.size() || customerNumber == 0){
                    throw new Exception();
                }
            } catch (Exception e){
                System.out.println("Customer doesn't exist..");
                customerNumber = 0;
            }
        } while (customerNumber == 0);

        // Apponitment Information
        String appointDate;
        Test testType;

        // Getting the date
        System.out.print("Enter Appointment Date (DD/MM/YYYY): ");
        input.nextLine();
        appointDate = input.nextLine();

        // Choosing Test Type
        System.out.println("What test do you want to make?");
        do {
            try {
                System.out.println("---( PCR | LFT | ART )---");
                testType = Test.valueOf(input.nextLine().toUpperCase());
            } catch (Exception e){
                System.out.println("Please choose a valid test...");
                testType = null;
            }
        } while (testType == null);

        // Creating an Appointment
        Appointment appointment = new Appointment(appointDate, testType);
        appointments.add(appointment);
        customers.get(customerNumber-1).bookAppointment(appointment);



        // Creating appointments file 
        String appointmentFileName = "appointmentFile.txt"; 

        try {  
            File f = new File(appointmentFileName);   
            f.createNewFile();
        } catch (IOException e) {}

        // Appending to the file
        try {  
            FileWriter appointmentFile = new FileWriter(appointmentFileName,true);
            appointmentFile.write(customerNumber + ", ");
            appointmentFile.write(appointDate + ", ");
            appointmentFile.write(testType + ", ");
            appointmentFile.write(testType.getTestCost() + "\n");
            appointmentFile.close();
        } catch (IOException e) {}

        System.out.println("The appointment has been booked, you will be redirected to the main menu...");
    }

    public static void DisplayAppointments() {
        System.out.println("---[4] View Appointments---");

        // Printing Allergic Patients
        if (appointments.size() > 0) System.out.println("\n----- Appointments -----");
        for (int i = 0 ; i < appointments.size(); i++){
            appointments.get(i).displayAppointment();
        }
    }

    public static void SetTestResult() {
        System.out.println("---[5] Set Test Result---");

        // Input Handler 
        Scanner input = new Scanner(System.in);
        int customerNumber;
        
        do {
            try {
                System.out.print("Enter customer number: ");
                customerNumber = input.nextInt();
                if (customerNumber > customers.size() || customerNumber == 0){
                    throw new Exception();
                }
            } catch (Exception e){
                System.out.println("Customer doesn't exist..");
                customerNumber = 0;
            }
        } while (customerNumber == 0);

        // Result Information
        String patientStatus = "none";
        String doctorNotes;

        // Getting Patient Status

        do {
            System.out.print("Enter Patient Status (POSITIVE/NEGATIVE): ");
            if(patientStatus.equals("none")) patientStatus = input.nextLine().toUpperCase();
            patientStatus = input.nextLine().toUpperCase();
        } while (!patientStatus.equals("POSITIVE") && !patientStatus.equals("NEGATIVE"));

        // Getting Doctor Notes
        System.out.print("Enter Doctor Notes: ");
        doctorNotes = input.nextLine();

        // Creating an Appointment
        Result result = new Result(patientStatus, doctorNotes);
        customers.get(customerNumber-1).setResult(result);

        System.out.println("The Test Result has been added, you will be redirected to the main menu...");
    }

    public static void ViewStatisics() {
        System.out.println("---[6] View Statisics---");

        // Printing total number of customers
        System.out.println("Number of Customers: " + customers.size());
        
        // Printing total number of appointments
        System.out.println("Number of Appointments: " + appointments.size());

        // Printing total income
        double totalIncome = 0.0;
        for (int i = 0 ; i < appointments.size(); i++) totalIncome = totalIncome + appointments.get(i).getCost();
        System.out.println("Total Income From Tests: $" + totalIncome);

        // Creating statistics file 
        String statisticsFileName = "statisticsFile.txt"; 

        try {  
            File f = new File(statisticsFileName);   
            f.createNewFile();
        } catch (IOException e) {}

        // OVERWRITE the file
        try {  
            PrintWriter statisticsFile = new PrintWriter(statisticsFileName);
            statisticsFile.println("Number of Customers: " + customers.size() );
            statisticsFile.println("Number of Appointments: " + appointments.size());
            statisticsFile.println("Total Income From Tests: $" + totalIncome);
            statisticsFile.println("");
            statisticsFile.close();
        } catch (IOException e) {}
    }



    public static void main(String args[]) {
        int choice;
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("|\tWelcome to our Lab\t|");
            System.out.println("|\t[1] Add New Customer\t|");
            System.out.println("|\t[2] View All Patients\t|");
            System.out.println("|\t[3] Make Appointment\t|");
            System.out.println("|\t[4] View Appointments\t|");
            System.out.println("|\t[5] Set Test Result\t|");
            System.out.println("|\t[6] View Statisics\t|");
            System.out.println("|\t[0] Exit\t\t|");
            System.out.println("---------------------------------");
            
            do {
                try {
                    System.out.print("Choice: ");
                    choice = input.nextInt();
                } catch (Exception e){
                    choice = -1;
                    input.next();
                }
            } while (choice == -1);

            switch (choice) {

                case 1:
                   CreateNewCustomer();
                   break;

                case 2:
                    DisplayPatients();
                    break;

                case 3:
                    MakeNewAppointment();
                    break;

                case 4:
                    DisplayAppointments();
                    break;

                case 5:
                    SetTestResult();
                    break;

                case 6:
                    ViewStatisics();
                    break;

                case 0:
                    System.out.println("Thanks for using our program!");
                    return;
                
                default:
                    System.out.println("incorrect choice!");
                
            }
        }
    }
}