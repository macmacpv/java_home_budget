package models;

import models.enums.CurrencyType;
import models.enums.ExpenseCategory;

import java.time.LocalDateTime;

public class Expense extends Operation {

    private ExpenseCategory category;

    public Expense(int accountId, CurrencyType currency, double amount, LocalDateTime date, String description, ExpenseCategory category) {
        super(accountId, currency, amount, date, description);
        this.category = category == null ? ExpenseCategory.OTHER : category;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        if (category == null) category = ExpenseCategory.OTHER;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "date='" + getDate().toString() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", category='" + category.toString() + '\'' +
                ", currency='" + getCurrency().toString()  + '\'' +
                ", amount=" + getAmount() +
                '}';
    }

    @Override
    public void execute(Account account) {
        //@TODO: Calculate currencies from account and operation
        account.withdraw(getAmount());
    }
}
