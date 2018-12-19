import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Polyline extends Items{
    Rectangle2D outRec;
//    ArrayList<Line2D> polylines = new ArrayList<>();
    private int[] xPlist;
    private int[] yPlist;
    private int nPlist;
    private int minX, maxX, minY, maxY;
    public Polyline(Graphics2D g2d, Color icolor, float width, int[] xPoints, int[] yPoints) {
        super(g2d, icolor, width, xPoints, yPoints);
        List<Integer> xList = Arrays.stream(xPoints).boxed().collect(Collectors.toList());
        List<Integer> yList = Arrays.stream(yPoints).boxed().collect(Collectors.toList());
        minX = Collections.min(xList);
        maxX = Collections.max(xList);
        minY = Collections.min(yList);
        maxY = Collections.max(yList);
        xPlist = xPoints;
        yPlist = yPoints;
        nPlist = xPlist.length;
//        Point2D fromPoint = new Point2D.Double((float)minX, (float)minY);
//        Point2D toPoint = new Point2D.Double((float)maxX, (float)maxY);
        float rwidth = (float)(maxX - minX);
        float rheight = (float)(maxY - minY);
        outRec = new Rectangle2D.Double((float)minX, (float)minY, rwidth, rheight);
        bound = outRec.getBounds2D();
    }

    @Override
    public void drawItems() {
        graphics.drawPolyline(xPlist, yPlist, nPlist);
    }


    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {
//        double deltaWidth = Math.abs(point2.getX() - point1.getX());
//        double deltaHeight = Math.abs(point2.getY() - point1.getY());
//        for(int i = 0; i < nPlist; i++) {
//            Point2D tempPoint = new Point2D.Double(xPlist[i], yPlist[i]);
//            pos = PressPos(tempPoint);
//            if(pos == L_UPPER) {
//
//            }
//            else if(pos == L_LOWER) {
//            }
//            else if(pos == R_UPPER) {
//            }
//            else if(pos == R_LOWER) {
//            }
//        }

    }

    @Override
    public void Move(Point2D point) {
        double deltaX = point.getX() - bound.getCenterX();
        double deltaY = point.getY() - bound.getCenterY();
        for(int i = 0; i < nPlist; i++) {
            xPlist[i] += deltaX;
            yPlist[i] += deltaY;
        }
        List<Integer> xList = Arrays.stream(xPlist).boxed().collect(Collectors.toList());
        List<Integer> yList = Arrays.stream(yPlist).boxed().collect(Collectors.toList());
        minX = Collections.min(xList);
        maxX = Collections.max(xList);
        minY = Collections.min(yList);
        maxY = Collections.max(yList);
        Point2D fromPoint = new Point2D.Double((float)minX, (float)minY);
        Point2D toPoint = new Point2D.Double((float)maxX, (float)maxY);
        outRec.setFrameFromDiagonal(fromPoint, toPoint);
        bound = outRec.getBounds2D();
    }

    @Override
    public String ReadItem() {
        return null;
    }
}
