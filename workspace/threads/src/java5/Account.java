package java5;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account {
    private double balance; //state --> heap --> instance variable --> Not safe
    Lock balLock = new ReentrantLock(); // Doug lea
    Lock profileLock = new ReentrantLock();
    Account(){}
    Account(double amt) {
        this.balance = amt;
    }

    public void updateProfile() {
        if(profileLock.tryLock()) {
            //
        }
    }
    public void deposit(String name, double amt){
        try {
           balLock.lock();
                System.out.println(name + " trying to deposit " + amt);
                System.out.println(name + " getting balance");
                double bal = getBalance();
                System.out.println(name + " got balance : " + bal);
                System.out.println(name + " setting balance");
                bal += amt;
                setBalance(bal);

        } finally {
            balLock.unlock();
        }
    }
    public void withdraw(String name, double amt){
        try {
//            balLock.lock();
            balLock.tryLock(2, TimeUnit.SECONDS);
                System.out.println(name + " trying to withdraw " + amt);
                System.out.println(name + " getting balance");
                double bal = getBalance();
                System.out.println(name + " got balance : " + bal);
                System.out.println(name + " setting balance");
                bal -= amt;
                setBalance(bal);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            balLock.unlock();
        }
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
