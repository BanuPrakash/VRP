package callableex;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class FlightsThread implements Callable<List<Flight>> {

    @Override
    public List<Flight> call() throws Exception {
        // make api call to backend to fetch data
        Thread.sleep(2000);
        return Arrays.asList(new Flight("IA123", "Indigo"),
                new Flight("AI632", "AirIndia"));
    }
}
