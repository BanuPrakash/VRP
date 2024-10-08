public class Account {
    private double balance; //state --> heap --> instance variable --> Not safe

    Account(){}
    Account(double amt) {
        this.balance = amt;
    }

    public  void deposit(String name, double amt){
        System.out.println(name + " trying to deposit " + amt);
        System.out.println(name + " getting balance");
        synchronized (this) {
            double bal = getBalance();
            System.out.println(name + " got balance : " + bal);
            System.out.println(name + " setting balance");
            bal += amt;
            setBalance(bal);
            notifyAll(); // notify();
        }
    }
    public synchronized void withdraw(String name, double amt){
        System.out.println(name + " trying to withdraw " + amt);
        System.out.println(name + " getting balance");
        int count = 0;
        while(amt > getBalance()) {
            System.out.println("Insufficient balance " + getBalance());
            count++;
            if(count > 3) {
                return;
            }
            try {
                wait(25000); // thread goes to wait state by releasing the lock
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
