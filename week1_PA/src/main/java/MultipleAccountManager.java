public interface MultipleAccountManager {
    void execute(Account a1, Account a2, int amount);
    boolean validate(Account account, int amount);
}
