package exercise;

import java.util.stream.IntStream;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;
    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        IntStream.range(0, 1000)
                .forEach(o -> {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add((int) (Math.random() * 100));
                });
    }
}
// END
