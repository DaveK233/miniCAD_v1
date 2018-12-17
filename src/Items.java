import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Items {
    // clockwise
    protected static final int L_LOWER = 0;
    protected static final int L_UPPER = 1;
    protected  static final int R_UPPER = 2;
    protected  static final int R_LOWER = 3;

    public Graphics2D graphics;
    public Color color;
    public int pos;
    public boolean select;

    public void SetSelected() {
        select = true;
    }

    public void UnSelected() {
        select = false;
        pos = -1;
    }

    public void SetColor(Color icolor) {
        color = icolor;
    }

    public Rectangle2D bound;
    public float stroke;

    public Stroke dash = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,3.5f,new float[]{15,10,},0f);

    public Items(Graphics2D g2d, Color icolor, float width, double x1, double y1, double boundw, double boundh)
    {
        graphics = g2d;
        color = icolor;
        pos = -1;
        stroke = width;
        select = false;
    }

    // make the stroke thicker or thinner
    public void ChageStroke(boolean input) {
        if(input && stroke < 5.0f) {
            stroke += 0.5f;
        }
        else if(stroke > 0.5f) {
            stroke -= 0.5f;
        }
    }

    // define new size to repaint
    public void Zoom(boolean input) {
        Point2D point1, point2;
        double tX = 0.1 * bound.getWidth();
        double tY = 0.1 * bound.getHeight();
        if(input) {
            // larger
            point1 = new Point2D.Double(bound.getX() - tX, bound.getY() - tY);
            point2 = new Point2D.Double(bound.getX() + tX + bound.getWidth(), bound.getY() + tY + bound.getHeight());
        }
        else {
            // smaller
            point1 = new Point2D.Double(bound.getX() + tX, bound.getY() + tY);
            point2 = new Point2D.Double(bound.getX() - tX + bound.getWidth(), bound.getY() - tY + bound.getHeight());
        }
        Resize(point1, point2, 1);  // Scale from the center of the image.
    }

    // judge if or not inside
    public boolean InsideJudge(Point2D point) {
        return bound.contains(point);
    }

    // position relative to the center
    public int PressPos(Point2D point) {
        // get select center
        Point2D centralPoint = new Point2D.Double(bound.getCenterX(), bound.getCenterY());
        double centralX = centralPoint.getX();
        double centralY = centralPoint.getY();
        if(point.getX() < centralX && point.getY() > centralY)
            return L_LOWER;
        else if(point.getX() < centralX && point.getY() < centralY)
            return L_UPPER;
        else if(point.getX() > centralX && point.getY() < centralY)
            return R_UPPER;
        else if(point.getX() > centralX && point.getY() > centralY)
            return R_LOWER;
        return -1;
    }

    // get diagonal point (fixed)
    public Point2D GetDiagonal(Point2D point) {
        pos = PressPos(point);
        Point2D RU_CORNER = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY());
        Point2D RL_CORNER = new Point2D.Double(bound.getX() + bound.getWidth(), bound.getY() + bound.getHeight());
        Point2D LU_CORNER = new Point2D.Double(bound.getX(), bound.getY());
        Point2D LL_CORNER = new Point2D.Double(bound.getX(), bound.getY() + bound.getHeight());
        if(pos == L_LOWER)
            return RU_CORNER;
        else if(pos == L_UPPER)
            return RL_CORNER;
        else if(pos == R_UPPER)
            return LL_CORNER;
        else if(pos == R_LOWER)
            return LU_CORNER;
        else return null;
    }

    // set params to draw
    public void Draw(Graphics g2d) {
        Graphics2D temp = graphics;
        graphics = (Graphics2D)g2d;
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(stroke));
        drawItems();
        if(select) {
            graphics.setColor(Color.BLACK);
            graphics.setStroke(dash);
            Rectangle2D dashRec = new Rectangle2D.Double(bound.getX() - 2, bound.getY() - 2, bound.getWidth() + 4, bound.getHeight() + 4);
            graphics.draw(dashRec);
        }
        graphics = temp;
    }

    public abstract void drawItems();
    public abstract void Resize(Point2D point1, Point2D point2, int centre);    // diagonal points & centre
    public abstract void Move(Point2D point);   // move to point
    public abstract String ReadItem();  // get params of an item

}
