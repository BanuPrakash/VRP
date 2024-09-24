package corruption;

public class Consumer extends Thread{
    Buffer buffer;
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run()
    {
        try {
            buffer.consume();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
