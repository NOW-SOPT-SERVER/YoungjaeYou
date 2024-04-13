import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DepositManager implements SingleAccountManager, AccountManager<Integer> {
    public boolean flag;
    public BufferedReader br;

    public DepositManager() {
        super();
        this.flag = false;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void excute(Account account, int amount) {
        account.setBalance(account.getBalance() + amount);
        this.flag = true;
    }

    @Override
    public void log() {
        if (this.flag) System.out.println("입금 성공.");
        else System.out.print("입금 실패. ");
    }

    @Override
    public Integer prompt() throws IOException {
        System.out.println("입금할 금액을 입력해주세요.");
        System.out.print(">> ");

        return Integer.parseInt(br.readLine().trim());
    }



}
