import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ellipse extends Items {

    private Ellipse2D ellipse;
    public Ellipse(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh) {
        super(g2d, icolor, width, x1, y1, boundw, boundh);
        ellipse = new Ellipse2D.Double(x1, y1, boundw, boundh);
        bound = ellipse.getBounds2D();
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
