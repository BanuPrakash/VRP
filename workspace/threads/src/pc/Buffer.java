package pc;

import java.util.LinkedList;

public class Buffer {
    int data;
    boolean isSet = false;

    // Function called by producer thread
    public synchronized void produce() throws InterruptedException
    {
        int value = 0;
        while (true) {
            // producer waits if data is already set
                if(isSet)
                    wait();
                System.out.println("Producer produced-"
                        + value);

               data = value++;
               isSet = true;
                // notifies the consumer thread that
                // now it can start consuming
                notify();
                Thread.sleep((long) (Math.random()*2000));
            }

    }

    // Function called by consumer thread
    public synchronized void consume() throws InterruptedException
    {
        while (true) {
                // consumer thread waits while not set
               if(!isSet)
                    wait(5000); // Thread.sleep(5000);
                int val = data;
                System.out.println("Consumer consumed-"
                        + val);
                isSet = false;
                // Wake up producer thread
                notify();
                Thread.sleep((long) (Math.random()*2000));
            }
        }

}
