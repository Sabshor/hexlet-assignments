package exercise;

import java.util.ArrayList;

class SafetyList {
    // BEGIN
    private ArrayList<Integer> list = new ArrayList<>();

    public Integer get(int index) {
        return list.get(index);
    }
    public synchronized void add(Integer num) {
        list.add(num);
    }
    public Integer getSize() {
        return list.size();
    }
    // END
}
