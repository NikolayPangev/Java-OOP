package bank.entities.loan;

public abstract class BaseLoan implements Loan{
    private int interestRate;
    private double amount;

    protected BaseLoan(int interestRate, double amount) {
        this.interestRate = interestRate;
        this.amount = amount;
    }
}
