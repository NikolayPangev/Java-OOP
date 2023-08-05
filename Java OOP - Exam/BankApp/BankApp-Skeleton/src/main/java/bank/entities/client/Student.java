package bank.entities.client;

import bank.entities.loan.BaseLoan;
//Can only live in BranchBank!
public class Student extends BaseClient {
    private static final int INITIAL_INTEREST_STUDENT = 2;

    public Student(String name, String ID, double income) {
        super(name, ID, INITIAL_INTEREST_STUDENT, income);
    }

    public void increase(){
        int currentInterest = getInterest();
        double onePercentOfInterest = currentInterest * 1;
        int newInterest = (int) (currentInterest + onePercentOfInterest);
        setInterest(newInterest);
    }
}
