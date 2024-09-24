package java5;

public class TransactionThread extends  Thread{
    private Account account;
    private String name;
    private TransactionType type;
    private double amount;

    public TransactionThread(Account account, String name, TransactionType type, double amount) {
        this.account = account;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        if(type == TransactionType.CREDIT) {
            account.deposit(name, amount);
        } else {
            account.withdraw(name, amount);
        }
    }
}
