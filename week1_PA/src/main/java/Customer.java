import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void createAccount(Account account) {
        this.accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getName() {
        return name;
    }
}
