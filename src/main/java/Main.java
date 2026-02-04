import models.Account;
import models.Operation;
import repository.FileRepository;
import repository.Repository;

public class Main {

    static void main(String[] args) {
        Repository<Account> accountRepo = new FileRepository<>("accounts.json", Account.class);
        Repository<Operation>  operationRepo = new FileRepository<>("operations.json", Operation.class);
    }

}
