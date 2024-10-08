import callableex.Flight;
import callableex.FlightsThread;
import callableex.Hotel;
import callableex.HotelsThread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankClient {
    public static void main(String[] args) {
        Account account = new Account(5000);
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        service.submit(new TransactionThread(account,
//                "Ria", TransactionType.CREDIT, 3500));
//
//        service.submit(new TransactionThread(account, "\tSwetha", TransactionType.DEBIT, 7000));


        TransactionThread t1 =
                new TransactionThread(account, "Ria", TransactionType.CREDIT, 3500);
        TransactionThread t2 =
                new TransactionThread(account, "\tSwetha", TransactionType.DEBIT, 7000);

        TransactionThread t3 =
                new TransactionThread(account, "\t\tAnna", TransactionType.CREDIT, 2500);

        t1.setName("Ria Thread");
        t2.setName("Swetha Thread");
        t3.setName("Anna Thread");
        t2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        t1.start();

        t3.start(); // main, t1, t2, t3 are ready / runnable
        System.out.println();
        try {
            t1.join(); // barrier , main thread waits for t1 to complete
            t2.join();// barrier , main thread waits for t2 to complete
            t3.join();// barrier , main thread waits for t3 to complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}
