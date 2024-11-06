package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    /*static int[] numbers = {10, -4, 67, 100, -100, 8};
    public static void main(String[] args) throws InterruptedException {
        System.out.println(App.getMinMax(numbers)); // => {min=-100, max=100}
    }*/
    public static Map<String, Integer> getMinMax(int[] numbers)  {
        MaxThread threadMax = new MaxThread(numbers);
        MinThread threadMin = new MinThread(numbers);
        threadMax.start();
        LOGGER.log(new LogRecord(Level.INFO, String.format("Thread %s started", threadMax)));
        threadMin.start();
        try {
            threadMax.join();
            threadMin.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("min", threadMin.getMin());
        map.put("max", threadMax.getMax());
        return map;
    }
    // END
}
