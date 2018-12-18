import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Polyline extends Items{
    Rectangle2D outRec;
    public Polyline(Graphics2D g2d, Color icolor, float width, int[] xPoints, int[] yPoints) {
        super(g2d, icolor, width, xPoints, yPoints);
        List<Integer> xList = Arrays.stream(xPoints).boxed().collect(Collectors.toList());
        List<Integer> yList = Arrays.stream(yPoints).boxed().collect(Collectors.toList());
        int minX = Collections.min(xList);
        int maxX = Collections.max(xList);
        int minY = Collections.min(yList);
        int maxY = Collections.max(yList);
        Point2D fromPoint = new Point2D.Double((float)minX, (float)minY);
        Point2D toPoint = new Point2D.Double((float)maxX, (float)maxY);
        float deltaX = (float)(maxX - minX);
        float deltaY = (float)(maxY - minY);
        outRec = new Rectangle2D.Double(fromPoint.getX(), fromPoint.getY(), deltaX, deltaY);
        bound = outRec.getBounds2D();
    }

    @Override
    public void drawItems() {
//        graphics.drawPolyline();
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
