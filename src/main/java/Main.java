import models.Account;
import models.Operation;
import models.enums.CurrencyType;
import repository.FileRepository;
import repository.Repository;
import service.BudgetService;

public class Main {

    static void main(String[] args) {
        Repository<Account> accountRepo = new FileRepository<>("accounts.json", Account.class);
        Repository<Operation> operationRepo = new FileRepository<>("operations.json", Operation.class);

        BudgetService budgetService = new BudgetService(accountRepo, operationRepo);
        budgetService.createAccount(new Account("Konto oszczędnościowe", CurrencyType.PLN, 0.0));
    }

}
