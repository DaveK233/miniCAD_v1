import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class FillEli extends Ellipse {

    private Ellipse2D ellipse;
    public FillEli(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh) {
        super(g2d, icolor, width, x1, y1, boundw, boundh);
        ellipse = new Ellipse2D.Double(x1, y1, boundw, boundh);
        bound = ellipse.getBounds2D();
    }

    @Override
    public void drawItems() {
        graphics.fill(ellipse);
    }

    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {
        double deltaWidth = Math.abs(point2.getX() - point1.getX());
        double deltaHeight = Math.abs(point2.getY() - point1.getY());
        pos = PressPos(point2);
            if(pos == L_UPPER) {
            ellipse.setFrameFromDiagonal(point1, new Point2D.Double(point1.getX() - deltaWidth, point1.getY() - deltaHeight));
        }
        else if(pos == L_LOWER) {
            ellipse.setFrameFromDiagonal(point1, new Point2D.Double(point1.getX() - deltaWidth, point1.getY() + deltaHeight));
        }
        else if(pos == R_UPPER) {
            ellipse.setFrameFromDiagonal(point1, new Point2D.Double(point1.getX() + deltaWidth, point1.getY() - deltaHeight));
        }
        else if(pos == R_LOWER) {
            ellipse.setFrameFromDiagonal(point1, new Point2D.Double(point1.getX() + deltaWidth, point1.getY() + deltaHeight));
        }
        bound = ellipse.getBounds2D();
    }

    @Override
    public void Move(Point2D point) {
        double newX = point.getX() - bound.getCenterX() + ellipse.getX();
        double newY = point.getY() - bound.getCenterY() + ellipse.getY();
        double newWidth = ellipse.getWidth();
        double newHeight = ellipse.getHeight();
        ellipse.setFrame(newX, newY, newWidth, newHeight);
        bound = ellipse.getBounds2D();
    }

    @Override
    public String ReadItem() {
        return "FILLELI," + color.getRGB() + "," + stroke + "," + (int)ellipse.getX() + "," + (int)ellipse.getY() + "," + (int)ellipse.getWidth() + "," + (int)ellipse.getHeight() + "\n";
    }
}
