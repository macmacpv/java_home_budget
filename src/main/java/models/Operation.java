package models;

import models.utils.CurrencyType;

import java.time.LocalDateTime;

public abstract class Operation {

    private int id;
    private CurrencyType currency;
    private double amount;
    private LocalDateTime date;
    private String description;

    public Operation(int id, CurrencyType currency, double amount, LocalDateTime date, String description) {
        this.id = id;
        this.currency = currency == null ? CurrencyType.USD : currency;
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        this.amount = amount;
        this.date = date == null ? LocalDateTime.now() : date;
        this.description = description == null ? "New operation" : description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        if (currency == null) currency = CurrencyType.USD;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) date = LocalDateTime.now();
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) description = "New operation";
        this.description = description;
    }

    public abstract void execute(Account account);

}
