package exercise;

// BEGIN
import java.util.List;

public class App {
   /* public static void main(String[] args) {
        List<Home> apartments2 = new ArrayList<>(List.of(
                new Cottage(100, 1),
                new Flat(190, 10, 2),
                new Flat(180, 30, 5),
                new Cottage(250, 3)
        ));
        System.out.println(buildApartmentsList(apartments2, 4));
    }*/

    public static List<String> buildApartmentsList(List<Home> apartments, int limit) {
        return apartments.stream()
                .sorted((a, b) -> Double.compare(a.getArea(), b.getArea()))
                .map(Home::toString)
                .limit(limit)
                .toList();
    }
}
// END
