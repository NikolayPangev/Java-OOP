package bank.entities.loan;

public class StudentLoan extends BaseLoan{
private static final int INTEREST_RATE_STUDENT_LOAN = 1;
private static final double AMOUNT_STUDENT_LOAN = 10000;
    public StudentLoan() {
        super(INTEREST_RATE_STUDENT_LOAN, AMOUNT_STUDENT_LOAN);
    }

    @Override
    public int getInterestRate() {
        return INTEREST_RATE_STUDENT_LOAN;
    }

    @Override
    public double getAmount() {
        return AMOUNT_STUDENT_LOAN;
    }
}
