package bank.entities.loan;

public class MortgageLoan extends BaseLoan{
    private static final int INTEREST_RATE_MORTGAGE_LOAN = 3;
    private static final double AMOUNT_MORTGAGE_LOAN = 50000;
    public MortgageLoan() {
        super(INTEREST_RATE_MORTGAGE_LOAN, AMOUNT_MORTGAGE_LOAN);
    }

    @Override
    public int getInterestRate() {
        return INTEREST_RATE_MORTGAGE_LOAN;
    }

    @Override
    public double getAmount() {
        return AMOUNT_MORTGAGE_LOAN;
    }
}
