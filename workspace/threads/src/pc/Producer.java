package pc;

public class Producer extends Thread{
    Buffer buffer;
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run()
    {
        try {
            buffer.produce();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
