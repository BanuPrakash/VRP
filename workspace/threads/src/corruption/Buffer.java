package corruption;

public class Buffer {
    int data;

    // Function called by producer thread
    public  void produce() throws InterruptedException
    {
        int value = 0;
        while (true) {
                System.out.println("Producer produced-"
                        + value);
               data = value++;

                Thread.sleep((long) (Math.random() * 3000));
            }
    }

    // Function called by consumer thread
    public  void consume() throws InterruptedException
    {
        while (true) {
                int val = data;
                System.out.println("Consumer consumed-"
                        + val);
            Thread.sleep((long) (Math.random() * 3000));
            }
        }
}
