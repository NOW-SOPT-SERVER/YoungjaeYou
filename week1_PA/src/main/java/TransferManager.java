import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TransferManager implements MultipleAccountManager, AccountManager<Integer> {
    private boolean flag;
    private String errorMsg = "";
    private BufferedReader br;

    public TransferManager() {
        super();
        this.flag = false;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute(Account to, Account from, int amount) {
        if(validate(to, amount)) {
            to.setBalance(to.getBalance() - amount);
            from.setBalance(from.getBalance() + amount);
            this.flag = true;
        }
        else this.errorMsg = "현재 계좌의 잔액이 부족하여 이체를 할 수 없습니다.";
    }

    @Override
    public void log() {
        if (this.flag) System.out.println("이체 성공.");
        else System.out.println("이체 실패. " + this.errorMsg);
    }

    @Override
    public Integer prompt() throws IOException {
        System.out.println("이체하실 계좌번호를 입력해주세요.");
        System.out.print(">> ");
        return Integer.parseInt(br.readLine().trim());
    }

    public Integer prompt2() throws IOException {
        System.out.println("이체하실 금액을 입력해주세요.");
        System.out.print(">> ");
        return Integer.parseInt(br.readLine().trim());
    }

    @Override
    public boolean validate(Account account, int amount) {
        return account.getBalance() >= amount;
    }

    public Account findByAccountNumber(int account_num) {
        for (Customer customer : Main.customerList) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccount_number() == account_num)
                    return account;
            }
        }
        this.errorMsg = "해당하는 계좌가 존재하지 않습니다.";
        return null;
    }
}
