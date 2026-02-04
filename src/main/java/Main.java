import models.Account;
import models.Operation;
import models.enums.CurrencyType;
import repository.FileRepository;
import repository.Repository;

public class Main {

    static void main(String[] args) {
        Repository<Account> accountRepo = new FileRepository<>("accounts.json", Account.class);
        Repository<Operation> operationRepo = new FileRepository<>("operations.json", Operation.class);

        accountRepo.save(new Account(null, CurrencyType.PLN, 10.0));
    }

}
