import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FillRec extends Rectangle{
    private Rectangle2D rectangle;
    public FillRec(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh) {
        super(g2d, icolor, width, x1, y1, boundw, boundh);
        rectangle = new Rectangle2D.Double(x1, y1, boundw, boundh);
        bound = rectangle.getBounds2D();
    }
    @Override
    public void drawItems() {
        graphics.fill(rectangle);
    }
    @Override
    public String ReadItem() {
        return "FILLREC," + color.getRGB() + "," + stroke + "," + (int)rectangle.getX() + "," + (int)rectangle.getY() + "," + (int)rectangle.getWidth() + "," + (int)rectangle.getHeight() + "\n";
    }
}
