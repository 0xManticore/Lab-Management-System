public enum Test {
    // tests prices
    PCR(250.00), 
    LFT(350.00),
    ART(200.00);


    private final double testCost;
    
    // enum constructor
    Test (double c) {
        testCost = c;
    }

    // cost accessor
    public double getTestCost(){
        return testCost;
    }
}
