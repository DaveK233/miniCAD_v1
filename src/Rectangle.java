import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Items {
    private Rectangle2D rectangle;
    public Rectangle(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh) {
        super(g2d, icolor, width, x1, y1, boundw, boundh);
        rectangle = new Rectangle2D.Double(x1, y1, boundw, boundh);
        bound = rectangle.getBounds2D();
    }

    @Override
    public void drawItems() {
        graphics.draw(rectangle);
    }

    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {
        rectangle.setFrameFromDiagonal(point1, point2);
        bound = rectangle.getBounds2D();
    }

    @Override
    public void Move(Point2D point) {
        double deltaX = point.getX() - bound.getCenterX();
        double deltaY = point.getY() - bound.getCenterY();
        double newX = rectangle.getX() + deltaX;
        double newY = rectangle.getY() + deltaY;
        double newWidth = rectangle.getWidth();
        double newHeight = rectangle.getHeight();
        rectangle.setFrame(newX, newY, newWidth, newHeight);
        bound = rectangle.getBounds2D();
    }

    @Override
    public String ReadItem() {
        return "RECTANGLE," + color.getRGB() + "," + stroke + "," + (int)rectangle.getX() + "," + (int)rectangle.getY() + "," + (int)rectangle.getWidth() + "," + (int)rectangle.getHeight() + "\n";
    }
}
