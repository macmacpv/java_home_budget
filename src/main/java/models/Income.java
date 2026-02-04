package models;

import models.enums.CurrencyType;

import java.time.LocalDateTime;

public class Income extends Operation {

    public Income(int id, int accountId, CurrencyType currency, double amount, LocalDateTime date, String description) {
        super(id, accountId, currency, amount, date, description);
    }

    @Override
    public String toString() {
        return "Income{" +
                "date='" + getDate().toString() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", currency='" + getCurrency().toString()  + '\'' +
                ", amount=" + getAmount() +
                '}';
    }

    @Override
    public void execute(Account account) {
        //@TODO: Calculate currencies from account and operation
        account.deposit(getAmount());
    }

}
