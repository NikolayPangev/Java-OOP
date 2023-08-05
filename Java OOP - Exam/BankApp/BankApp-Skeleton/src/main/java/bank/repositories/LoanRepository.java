package bank.repositories;

import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;

public class LoanRepository implements Repository {

    private Collection<Loan> loans;

    public LoanRepository() {
        loans = new ArrayList<>();
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public boolean removeLoan(Loan loan) {
        return loans.remove(loan);
    }

    @Override
    public Loan findFirst(String type) {
        for (Loan loan : loans) {
            if (loan.getClass().getSimpleName().equals(type)) {
                return loan;
            }
        }
        return null;
    }
}
