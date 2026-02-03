package models;

import models.utils.CurrencyType;

import java.util.*;

public class Account {

    private int id;
    private String name;
    private double balance;
    private CurrencyType currency;
    private Map<Integer, Operation> operations;

    public Account(int id, String name, CurrencyType currency, double balance, Map<Integer, Operation> operations) {
        this.id = id;
        this.name = name == null ? "Default account" : name;
        this.currency = currency == null ? CurrencyType.USD : currency;
        this.balance = balance;
        this.operations = operations == null ? new HashMap<>() : operations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) name = "Default account";
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

    public Map<Integer, Operation> getOperations() {
        return new HashMap<>(operations);
    }

    public void setOperations(Map<Integer, Operation> operations) {
        if (operations == null) operations = new HashMap<>();
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
