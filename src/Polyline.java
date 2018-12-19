import java.awt.*;
import java.awt.geom.AffineTransform;
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
        double newWidth = Math.abs(point2.getX() - point1.getX());
        double newHeight = Math.abs(point2.getY() - point1.getY());
        double zoomScaleX = newWidth / (bound.getWidth());
        double zoomScaleY = newHeight / (bound.getHeight());
        for(int i = 0; i < nPlist; i++) {
            Point2D tempPoint = new Point2D.Double((double)xPlist[i], (double)yPlist[i]);
            pos = PressPos(tempPoint);
            double oldDeltaX = Math.abs(bound.getCenterX() - xPlist[i]);
            double oldDeltaY = Math.abs(bound.getCenterY() - yPlist[i]);
            double newDeltaX = oldDeltaX * zoomScaleX;
            double newDeltaY = oldDeltaY * zoomScaleY;
            if(pos == L_UPPER) {
                xPlist[i] = (int) (bound.getCenterX() - newDeltaX);
                yPlist[i] = (int) (bound.getCenterY() - newDeltaY);
            }
            else if(pos == L_LOWER) {

                xPlist[i] = (int) (bound.getCenterX() - newDeltaX);
                yPlist[i] = (int) (bound.getCenterY() + newDeltaY);
            }
            else if(pos == R_UPPER) {
                xPlist[i] = (int) (bound.getCenterX() + newDeltaX);
                yPlist[i] = (int) (bound.getCenterY() - newDeltaY);
            }
            else if(pos == R_LOWER) {
                xPlist[i] = (int) (bound.getCenterX() + newDeltaX);
                yPlist[i] = (int) (bound.getCenterY() + newDeltaY);
            }
        }
//        AffineTransform aTransform = new AffineTransform();
////        graphics.setTransform(aTransform);
//        double newWidth = Math.abs(point2.getX() - point1.getX());
//        double newHeight = Math.abs(point2.getY() - point1.getY());
//        double zoomScaleX = newWidth / (bound.getWidth());
//        double zoomScaleY = newHeight / (bound.getHeight());
//        aTransform.setToTranslation(100,100);
//        aTransform.scale(10, 10);
//        graphics.setTransform(aTransform);
//        graphics.draw();
////        graphics.scale(10, 10);
        outRec.setFrameFromDiagonal(point1, point2);
        bound = outRec.getBounds2D();
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
        StringBuilder info = new StringBuilder("POLYLINE," + color.getRGB() + "," + stroke + "," + nPlist);
        for(int i = 0; i < nPlist; i++) {
            info.append(",").append(xPlist[i]);
        }
        for(int i = 0; i < nPlist; i++) {
            info.append(",").append(yPlist[i]);
        }
        info.append("\n");
        return info.toString();
    }
}
