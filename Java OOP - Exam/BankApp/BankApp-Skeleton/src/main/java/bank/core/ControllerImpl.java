package bank.core;

import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;

import static bank.common.ConstantMessages.*;
import static bank.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private LoanRepository loans;
    private Collection<Bank> banks;
    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new ArrayList<>();
    }
    @Override
    public String addBank(String type, String name) {
        Bank bank;
        switch (type) {
            case "CentralBank":
                bank = new CentralBank(name);
                break;
            case "BranchBank":
                bank = new BranchBank(name);
                break;
            default:
                throw new IllegalArgumentException(INVALID_BANK_TYPE);
        }
        banks.add(bank);
        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        Loan loan;
        switch (type) {
            case "StudentLoan":
                loan = new StudentLoan();
                break;
            case "MortgageLoan":
                loan = new MortgageLoan();
                break;
            default:
                throw new IllegalArgumentException(INVALID_LOAN_TYPE);
        }
        loans.addLoan(loan);
        return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Loan loan = loans.findFirst(loanType);
        if (loan == null) {
            throw new IllegalArgumentException(String.format(NO_LOAN_FOUND, loanType));
        }

        Bank bank = findTheBankByItsName(bankName);
        if (bank == null) {
            throw new IllegalArgumentException(UNSUITABLE_BANK);
        }

        bank.addLoan(loan);
        loans.removeLoan(loan);

        return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }
    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        Bank bank = findTheBankByItsName(bankName);
        if (bank == null) {
            throw new IllegalArgumentException(UNSUITABLE_BANK);
        }

        if (!isValidClientType(clientType, bank)) {
            throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }

        Client client;
        if (clientType.equals("Student")) {
            client = new Student(clientName, clientID, income);
        } else if (clientType.equals("Adult")) {
            client = new Adult(clientName, clientID, income);
        } else {
            throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }

        try {
            bank.addClient(client);
            return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
        } catch (IllegalStateException exception) {
            return NOT_ENOUGH_CAPACITY_FOR_CLIENT;
        }
    }
    @Override
    public String finalCalculation(String bankName) {
        Bank bank = findTheBankByItsName(bankName);
        if (bank == null) {
            throw new IllegalArgumentException(UNSUITABLE_BANK);
        }

        double totalFunds = calculateTotalFunds(bank);
        return String.format(FUNDS_BANK, bankName, totalFunds);
    }
    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Bank bank : banks) {
            stringBuilder.append("Name: ").append(bank.getName())
                    .append(", Type: ").append(bank.getClass().getSimpleName()).append("\n");

            Collection<Client> clients = bank.getClients();
            stringBuilder.append("Clients: ");
            if (clients.isEmpty()) {
                stringBuilder.append("none");
            } else {
                clients.forEach(client -> stringBuilder.append(client.getName()).append(", "));
                stringBuilder.setLength(stringBuilder.length() - 2);
            }
            stringBuilder.append("\n");

            Collection<Loan> loans = bank.getLoans();
            stringBuilder.append("Loans: ").append(loans.size())
                    .append(", Sum of interest rates: ").append(bank.sumOfInterestRates()).append("\n");

            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private Bank findTheBankByItsName(String bankName) {
        for (Bank bank : banks) {
            if (bank.getName().equals(bankName)) {
                return bank;
            }
        }
        return null;
    }

    private boolean isValidClientType(String clientType, Bank bank) {
        if (bank instanceof CentralBank && !clientType.equals("Adult")) {
            return false;
        }
        if (bank instanceof BranchBank && !clientType.equals("Student")) {
            return false;
        }
        return true;
    }

    private double calculateTotalFunds(Bank bank) {
        double totalIncome = bank.getClients().stream()
                .mapToDouble(Client::getIncome)
                .sum();

        double totalLoanAmount = bank.getLoans().stream()
                .mapToDouble(Loan::getAmount)
                .sum();

        return totalIncome + totalLoanAmount;
    }
}
