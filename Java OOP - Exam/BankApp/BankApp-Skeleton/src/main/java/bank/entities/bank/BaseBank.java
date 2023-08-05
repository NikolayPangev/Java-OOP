package bank.entities.bank;

import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;

import static bank.common.ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY;
import static bank.common.ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT;

public class BaseBank implements Bank{
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Client> getClients() {
        return clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return loans;
    }

    @Override
    public void addClient(Client client) {
        if (clients.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        int totalInterest = 0;
        for (Loan loan : loans) {
            totalInterest += loan.getInterestRate();
        }
        return totalInterest;
    }

    @Override
    public String getStatistics() {
        StringBuilder statistics = new StringBuilder();
        statistics.append("Name: ").append(getName()).append(", Type: ").append(getClass().getSimpleName()).append("\n");

        if (!clients.isEmpty()) {
            statistics.append("Clients: ");
            for (Client client : clients) {
                statistics.append(client.getName()).append(", ");
            }
            statistics.setLength(statistics.length() - 2);
        } else {
            statistics.append("Clients: none");
        }

        statistics.append("\nLoans: ").append(loans.size()).append(", Sum of interest rates: ").append(sumOfInterestRates());

        return statistics.toString();
    }
}
