package exercise;



// BEGIN
import lombok.Getter;
@Getter
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public int compareTo(Home anotherObj) {
        if (getArea() == anotherObj.getArea()) {
            return 0;
        }
        return getArea() > anotherObj.getArea() ? 1 : -1;
    }

    public String toString() {
        return String.format("%d этажный коттедж площадью %s метров", floorCount, getArea());
    }
}
// END
