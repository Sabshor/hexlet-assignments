package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;
    private int min;
    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        // Метод getName() выводит имя потока
        System.out.println("Thread " + getName());
        min = Arrays.stream(numbers).min().getAsInt();
    }

    public int getMin() {
        return min;
    }
}
// END
