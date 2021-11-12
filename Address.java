public class Address {

    // attributes
    private String street;
    private String city;
    private String state;
    private int postcode;

    // default constructor
    Address() {
        street = "";
        city = "";
        state = "";
    }

    // 4-args constructor
    Address(String s, String c, String t, int p) {
        street = s;
        city = c;
        state = t;
        postcode = p;
    }

    // full address accessor method
    public String getFullAddress(){
        return street + ", " + city + ", " + state + " " + postcode;
    }
    
}
