import java.io.IOException;

public interface AccountManager <T> {
    void log();
    T prompt() throws IOException;
    default void printBalance(Account account) {
        System.out.println("현재 " + account.getAccount_number() + " 계좌의 잔액은 " + account.getBalance() + "원 입니다.");
    }
}
