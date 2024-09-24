package callableex;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws  Exception{
        // thread pool
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<List<Flight>> flightsFuture = service.submit(new FlightsThread());
        Future<List<Hotel>> hotelsFuture = service.submit(new HotelsThread());

        // blocked
        System.out.println(flightsFuture.get());
        System.out.println(hotelsFuture.get());



        service.shutdown();
    }
}
