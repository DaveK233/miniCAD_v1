import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Items {

    private Line2D line;
    public Line(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh) {
        super(g2d, icolor, width, x1, y1, boundw, boundh);
        line = new Line2D.Double(x1, y1, boundw, boundh);   // set params
        bound = line.getBounds2D(); // get bounding box
    }

    @Override
    public void drawItems() {
        graphics.draw(line);    // draw line
    }

    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {
        if(centre != 1) {
            // Resize mode 1: resize without stationary centre
            if(point1.equals(line.getP1()) || point1.equals(line.getP2()) || point2.equals(line.getP1()) || point2.equals(line.getP2())) {
                line.setLine(point1, point2);
            }
            else
                // reverse points
                line.setLine(new Point2D.Double(point1.getX(), point2.getY()), new Point2D.Double(point2.getX(), point2.getY()));
        }
        else {
            // Resize mode 2: resize with centre pinned
            if(line.getX1() < bound.getCenterX() && line.getY1() < bound.getCenterY()
            || line.getX2() < bound.getCenterX() && line.getY2() < bound.getCenterY())
                line.setLine(point1, point2);
            else
                // reverse points
                line.setLine(new Point2D.Double(point1.getX(), point2.getY()), new Point2D.Double(point2.getX(), point1.getY()));
        }
        bound = line.getBounds2D(); // get new bounding box
    }

    @Override
    public void Move(Point2D point) {
        double deltaY = point.getY() - bound.getCenterY();  // calculate delta X
        double deltaX = point.getX() - bound.getCenterX();  // calculate delta Y
        Point2D newP1 = new Point2D.Double(line.getX1() + deltaX, line.getY1() + deltaY);
        Point2D newP2 = new Point2D.Double(line.getX2() + deltaX, line.getY2() + deltaY);
        line.setLine(newP1, newP2); // draw new line
        bound = line.getBounds2D();
    }

    @Override
    public String ReadItem() {
        return "LINE," + color.getRGB() + "," + stroke + "," + (int)line.getX1() + "," + (int)line.getY1() + "," + (int)line.getX2() + "," + (int)line.getY2() + "\n";
    }
}
