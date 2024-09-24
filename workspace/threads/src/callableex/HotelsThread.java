package callableex;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class HotelsThread implements Callable<List<Hotel>> {
    @Override
    public List<Hotel> call() throws Exception {
        // make api call to backend to fetch data
        Thread.sleep(3000);
        return Arrays.asList(new Hotel("HolidayInn"),
                new Hotel("LemonTree"));
    }
}
