import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Items {
    private Rectangle2D rectangle;
    public Rectangle(Graphics2D g2d, Color icolor, float width, double x1, double y1, double x2, double y2) {
        super(g2d, icolor, width, x1, y1, x2, y2);
        rectangle = new Rectangle2D.Double(x1, y1, x2, y2);

    }

    @Override
    public void drawItems() {

    }

    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {

    }

    @Override
    public void Move(Point2D point) {

    }

    @Override
    public String ReadItem() {
        return null;
    }
}
