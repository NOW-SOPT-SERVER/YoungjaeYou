import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String LINE = "-----------------------------------------";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    protected static List<Customer> customerList;

    public static void main(String[] args) throws IOException {
        setUp();

        while (true) {
            Customer customer = login(); // 로그인
            while (true) {
                Account account = selectAccount(customer); // 계좌 선택
                while (true) {
                    Operation operation = selectMenu(); // 메뉴 선택
                    if (operation == Operation.EXIT) return;
                    if (operation == Operation.GO_BACK) break;
                    if (operation == Operation.INVALID_SELECTION) continue;
                    account.accountOperation(operation); // 메뉴 실행
                }
            }
        }

    }

    // 초기 세팅
    public static void setUp() {
        // 더미 데이터
        Customer c1 = new Customer("geniuus");
        Customer c2 = new Customer("youngjae");

        for (int i = 0; i < 3; i++) {
            c1.createAccount(new Account(12345));
            c2.createAccount(new Account(12345));
        }
        customerList = new ArrayList<>();
        customerList.add(c1);
        customerList.add(c2);

        System.out.println(LINE);
        System.out.println("안녕하세요 솝트뱅크입니다.");
    }

    // 이름으로 로그인
    public static Customer login() throws IOException {
        while (true) {
            System.out.print("이름을 입력해주세요. \n>> ");

            String name = br.readLine().trim();

            for (Customer customer : customerList) {
                if (customer.getName().equals(name)) {
                    System.out.println("환영합니다 " + name + "님 !");
                    return customer;
                }
            }
        }
    }

    // 계좌 선택
    public static Account selectAccount(Customer customer) throws IOException {
        while (true) {
            System.out.println("계좌를 선택해주세요.");

            List<Account> accountList = customer.getAccounts();

            for (int i = 0; i < customer.getAccounts().size(); i++) {
                System.out.println(i+1 + ". " + accountList.get(i).getAccount_number() + " 계좌");
            }
            System.out.print(">> ");

            int selected = Integer.parseInt(br.readLine().trim()) - 1;

            System.out.print(accountList.get(selected).getAccount_number() + " 계좌의 비밀번호를 입력해주세요.\n>> ");
            int password = Integer.parseInt(br.readLine().trim());

            if (accountList.get(selected).validatePassword(password))
                return accountList.get(selected);
        }
    }

    // 메뉴 선택
    public static Operation selectMenu() throws IOException {
        System.out.println("숫자만을 사용해 메뉴를 선택해주세요.");
        System.out.println(
                "1. 입금 \n" +
                "2. 출금 \n" +
                "3. 계좌 이체 \n" +
                "4. 잔액 조회 \n" +
                "5. 계좌 선택으로 돌아가기 \n" +
                "0. 종료");
        System.out.print(">> ");
        int selected = Integer.parseInt(br.readLine().trim());

        switch (selected) {
            case 1 : return Operation.DEPOSIT;
            case 2 : return Operation.WITHDRAW;
            case 3 : return Operation.TRANSFER;
            case 4 : return Operation.BALANCE_INQUIRY;
            case 5 : return Operation.GO_BACK;
            case 0 : {
                System.out.println("프로그램을 종료합니다.");
                return Operation.EXIT;
            }
            default : {
                System.out.println("없는 메뉴입니다. 다시 선택해주세요.");
                return Operation.INVALID_SELECTION;
            }
        }
    }

}
