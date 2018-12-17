import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Text extends Items{
    private String text;
    private Font font;
    private FontMetrics attr;
    public Text(Graphics2D g2d, Color icolor, String str, float width, double x1, double y1, double x2, double y2) {
        super(g2d, icolor, width, x1, y1, x2, y2);
        font = new Font("Consolas", Font.PLAIN, 20);
        if(str == null) {
            text = JOptionPane.showInputDialog("Input text:");
        }
        else {
            text = str;
        }
        attr = g2d.getFontMetrics(font);
        bound = new Rectangle2D.Double(x1, y1, attr.stringWidth(text), attr.getAscent());
        this.Resize(new Point2D.Double(x1, y1), new Point2D.Double(x1 + x2, y1 + y2), -1);
    }

    @Override
    public void drawItems() {
        graphics.setFont(font);
        attr = graphics.getFontMetrics(font);
        graphics.drawString(text, (int)bound.getX(), (int)bound.getY()+(int)bound.getHeight());
    }

    @Override
    public void Resize(Point2D point1, Point2D point2, int centre) {
        bound.setFrameFromDiagonal(point1, point2);
        font = font.deriveFont(Font.BOLD);
        attr = graphics.getFontMetrics(font);
        while(true) {
            if(attr.stringWidth(text) <= bound.getWidth() && attr.getAscent() <= bound.getHeight()) {
                font = font.deriveFont(font.getSize2D() + 1);
                attr = graphics.getFontMetrics(font);
            }
            else {
                font = font.deriveFont(font.getSize2D() - 1);
                attr = graphics.getFontMetrics(font);
                break;
            }
        }
    }

    @Override
    public void Move(Point2D point) {
        double newX = point.getX() - bound.getCenterX() + bound.getX();
        double newY = point.getY() - bound.getCenterY() + bound.getY();
        bound.setFrame(newX, newY, bound.getWidth(), bound.getHeight());
    }

    @Override
    public String ReadItem() {
        return "TEXT," + color.getRGB() + "," + stroke + "," + text + "," + (int)bound.getX() + "," + (int)bound.getY() + "," + (int)bound.getWidth() + "," + (int)bound.getHeight() + "\n";
    }
}
