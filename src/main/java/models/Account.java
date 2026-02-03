package models;

import models.utils.CurrencyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {

    private final int id;
    private String name;
    private double balance;
    private CurrencyType currency;
    private List<Operation> operations;

    public Account(int id, String name, CurrencyType currency, double balance, List<Operation> operations) {
        //@TODO: id from repository map
        this.id = id;
        this.name = name == null ? "Konto domyślne" : name;
        this.currency = currency == null ? CurrencyType.USD : currency;
        this.balance = balance;
        this.operations = operations == null ? new ArrayList<>() : operations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) name = "Konto domyślne";
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        if (currency == null) currency = CurrencyType.USD;
        this.currency = currency;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        if (operations == null) operations = new ArrayList<>();
        this.operations = operations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }

}
