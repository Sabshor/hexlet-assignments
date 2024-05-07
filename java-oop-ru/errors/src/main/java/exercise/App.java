package exercise;

// BEGIN
public class App {
    public static void main(String[] args) {
        /*Point point = new Point(5, 7);
        Circle circle = new Circle(point, 5);
       //Circle circle2 = new Circle(point, -2);
        App.printSquare(circle);*/
    }

    public static void printSquare(Circle circle) {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь");
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
