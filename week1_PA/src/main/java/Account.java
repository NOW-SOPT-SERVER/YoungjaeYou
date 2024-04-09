import java.io.IOException;
import java.util.Random;

public class Account {
    private int account_number;
    private int password;
    private int balance;

    public Account(int password) {
        this.account_number = Integer.parseInt(String.valueOf(new Random().nextInt(90000) + 10000));
        this.password = password;
        this.balance = 0;
    }

    public void accountOperation(Operation operation) throws IOException {
        switch (operation) {
            case DEPOSIT -> {
                DepositManager depositManager = new DepositManager();
                depositManager.excute(this, depositManager.prompt());
                depositManager.log();
                depositManager.printBalance(this);
            }
            case WITHDRAW -> {
                WithdrawManager withdrawManager = new WithdrawManager();
                withdrawManager.excute(this, withdrawManager.prompt());
                withdrawManager.log();
                withdrawManager.printBalance(this);
            }
            case TRANSFER -> {
                TransferManager transferManager = new TransferManager();
                Account account = transferManager.findByAccountNumber(transferManager.prompt());
                if (account != null) {
                    transferManager.execute(this, account, transferManager.prompt2());
                }
                transferManager.log();
                transferManager.printBalance(this);
            }
            case BALANCE_INQUIRY -> this.printBalance();

        }
    }

    public void printBalance() {
        System.out.println("현재 " + this.account_number + " 계좌의 잔액은 " + this.getBalance() + "원 입니다.");
    }

    public int getAccount_number() {
        return account_number;
    }

    public int getBalance() {
        return balance;
    }

    public Account setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public boolean validatePassword(int password) {
        return this.password == password;
    }

}
