package service;

import models.Account;
import models.Expense;
import models.Income;
import models.Operation;
import models.enums.CurrencyType;
import models.enums.ExpenseCategory;
import repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetService {

    private final Repository<Account> accountRepo;
    private final Repository<Operation> operationRepo;

    public BudgetService(Repository<Account> accountRepo, Repository<Operation> operationRepo) {
        this.accountRepo = accountRepo;
        this.operationRepo = operationRepo;
    }

    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    public List<Operation> getAccountOperations(int accountId) {
        return operationRepo.findAll()
                .stream().filter(operation -> operation.getAccountId() == accountId)
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }

    public void addIncome(int accountId, CurrencyType currencyType, double amount, String description) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Income operation = new Income(accountId, currencyType, amount, LocalDateTime.now(), description);
        operation.execute(account);

        accountRepo.save(account);
        operationRepo.save(operation);
    }

    public void addExpense(int accountId, CurrencyType currencyType, double amount, String description, ExpenseCategory expenseCategory) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Expense operation = new Expense(accountId, currencyType, amount, LocalDateTime.now(), description, expenseCategory);
        operation.execute(account);

        accountRepo.save(account);
        operationRepo.save(operation);
    }

}
