import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WithdrawManager implements SingleAccountManager, AccountManager<Integer> {
    private boolean flag;
    private String errorMsg = "";
    private BufferedReader br;

    public WithdrawManager() {
        super();
        this.flag = false;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void excute(Account account, int amount) {
        if (validate(account, amount)) {
            account.setBalance(account.getBalance() - amount);
            this.flag = true;
        }
        else errorMsg = "출금 가능 잔액이 부족합니다.";
    }

    @Override
    public void log() {
        if (this.flag) System.out.println("출금 성공.");
        else System.out.println("출금 실패. " + errorMsg);
    }

    @Override
    public Integer prompt() throws IOException {
        System.out.println("출금할 금액을 입력해주세요.");
        System.out.print(">> ");
        return Integer.parseInt(br.readLine().trim());
    }

    public boolean validate(Account account, int amount) {
        return account.getBalance() >= amount;
    }

}
