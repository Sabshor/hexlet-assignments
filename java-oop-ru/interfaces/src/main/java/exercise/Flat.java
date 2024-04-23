package exercise;

// BEGIN
import lombok.Getter;

@Getter
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home anotherObj) {
        if (getArea() == anotherObj.getArea()) {
            return 0;
        }
        return getArea() > anotherObj.getArea() ? 1 : -1;
    }

    public String toString() {
        return String.format("Квартира площадью %s метров на %d этаже", getArea(), floor);
    }
}
// END
