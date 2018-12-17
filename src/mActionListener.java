import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class mActionListener implements MouseListener, MouseMotionListener, ActionListener, KeyListener {
//    //Different states for create, scale and movement
//    public enum Action{EMPTY, CREATEINIT, CREATE, SCALEINIT, GETPOSITION, SCALE, MOVEINIT, MOVECONFIM,  MOVE}
//    private myItemController myItemController;	//controller for all items
//    private myMainFrame mainFrame;	//current mainframe
//    private Action currentAction;	//current action
//    private Items currentItem;		//current item
//    private String createType;		//creating type: line, circle, rectangle or text
//    private Point2D createPoint;	//creating position
//    private Point2D resizePoint;	//resizing point
//    private static Point2D pressedPoint = new Point2D.Double();	//point of mouse pressing
//    private Color color;			//paint color
//    private boolean pressedLeft;	//if last press is on the left

    private ControlPanel controller;
    private MainFrame mainFrame;
    private Action action;
    private Items item;
    private String opNew;
    private Point2D initPos;
    private Point2D newPos;


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
