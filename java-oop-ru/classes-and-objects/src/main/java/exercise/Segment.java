package exercise;

// BEGIN
import lombok.Getter;

@Getter
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point pointStart, Point pointEnd) {
        this.beginPoint = pointStart;
        this.endPoint = pointEnd;
    }

    public Point getMidPoint() {
        int normalizeEndX = endPoint.getX();
        normalizeEndX = normalizeEndX < 0 ? -normalizeEndX : normalizeEndX;
        int normalizeEndY = endPoint.getY();
        normalizeEndY = normalizeEndY < 0 ? -normalizeEndY : normalizeEndY;
        int midX = (beginPoint.getX() + normalizeEndX) / 2;
        int midY = (beginPoint.getY() + normalizeEndY) / 2;
        return new Point(midX, midY);
    }
}
// END
