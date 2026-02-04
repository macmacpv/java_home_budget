package models;

import models.enums.CurrencyType;
import models.interfaces.Identifiable;
import repository.Repository;

import java.util.*;

public class Account implements Identifiable {

    private int id = 0;
    private String name;
    private double balance;
    private CurrencyType currency;

    public  Account(String name, CurrencyType currency, double balance) {
        this.name = name == null ? "Default account" : name;
        this.currency = currency == null ? CurrencyType.USD : currency;
        this.balance = balance;
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
