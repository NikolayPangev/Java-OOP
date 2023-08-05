package bank.entities.client;
//Can only live in CentralBank!
public class Adult extends BaseClient {
    private static final int INITIAL_INTEREST_ADULT = 4;

    public Adult(String name, String ID, double income) {
        super(name, ID, INITIAL_INTEREST_ADULT, income);
    }

    @Override
    public void increase() {
        int currentInterest = getInterest();
        double onePercentOfInterest = currentInterest * 2;
        int newInterest = (int) (currentInterest + onePercentOfInterest);
        setInterest(newInterest);
    }
}
