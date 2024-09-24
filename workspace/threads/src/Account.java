public class Account {
    private double balance; //state --> heap --> instance variable --> Not safe

    Account(){}
    Account(double amt) {
        this.balance = amt;
    }

    public void deposit(String name, double amt){
        System.out.println(name + " trying to deposit " + amt);
        System.out.println(name + " getting balance");
        double bal = getBalance();
        System.out.println(name + " got balance : " + bal);
        System.out.println(name + " setting balance");
        bal += amt;
        setBalance(bal);
    }
    public void withdraw(String name, double amt){
        System.out.println(name + " trying to withdraw " + amt);
        System.out.println(name + " getting balance");
        double bal = getBalance();
        System.out.println(name + " got balance : " + bal);
        System.out.println(name + " setting balance");
        bal -= amt;
        setBalance(bal);
    }
    public double getBalance() {
        // simulate network delay
        try {
            Thread.sleep((long)(Math.random()*2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    private void setBalance(double balance) {
        try {
            Thread.sleep((long)(Math.random()*2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.balance = balance;
    }
}
