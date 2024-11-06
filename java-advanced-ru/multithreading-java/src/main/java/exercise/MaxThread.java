package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;
    private int max;
    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        System.out.println("Thread " + getName());
        max = Arrays.stream(numbers).max().getAsInt();
    }

    public int getMax() {
        return max;
    }
}
// END
