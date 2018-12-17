import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class mActionListener implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    public enum Action{EMPTY, INITNEW, NEW, INITZOOM, ZOOM, GETPOS, INITMOV, TOMOVE, MOV}
    private ControlPanel controller;
    private MainFrame mainFrame;
    private Action action;
    private Items item;
    private String opNew;
    private static Point2D clickPoint = new Point2D.Double();
    private Point2D initPos;
    private Point2D newPos;
    private boolean leftClicked;
    private boolean rightClicked;
    private Color color;

    public mActionListener(ControlPanel cont) {
        controller = cont;
        action = Action.EMPTY;
    }

    public void Frame(MainFrame frame) {
        mainFrame = frame;
    }

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
        Component comp = e.getComponent();
        clickPoint.setLocation(e.getX(), e.getY());
        if(comp instanceof mCanvas) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(item != null) {
                    item.UnSelected();
                }
                if(action == Action.EMPTY) {
                    item = controller.Select(clickPoint);
                    if(item != null) {
                        item.SetSelected();
                    }
                }
                else if(action == Action.INITNEW) {
                    if(opNew.equals("LINE")) {
                        item = controller.newItem(clickPoint.getX(), clickPoint.getY(), clickPoint.getX(), clickPoint.getY(), opNew);
                    }
                    else {
                        item = controller.newItem(clickPoint.getX(), clickPoint.getY(), 0, 0, opNew);
                    }
                    item.SetSelected();
                    initPos = new Point2D.Double(clickPoint.getX(), clickPoint.getY());
                    action = Action.NEW;
                }
                else if(action == Action.NEW) {
                    // to update: assert or if
                    if (item != null) {
                        item.Resize(newPos, clickPoint, -1);
                        item = null;
                        action = Action.INITNEW;
                    }
                }
                else if(action == Action.INITMOV) {
                    item = controller.Select(clickPoint);
                    if(item != null) {
                        item.SetSelected();
                        action = Action.TOMOVE;
                    }
                }
                else if(action == Action.TOMOVE) {
                    if(controller.Select(clickPoint) == item) {
                        item.SetSelected();
                        action = Action.MOV;
                    }
                    else {
                        item = null;
                        action = Action.INITMOV;
                    }
                }
                else if(action == Action.MOV) {
                    if (item != null) {
                        item.Move(clickPoint);
                        item = null;
                        action = Action.INITMOV;
                    }
                }
                else if(action == Action.INITZOOM) {
                    item = controller.Select(clickPoint);
                    if(item != null) {
                        item.SetSelected();
                        action = Action.GETPOS;
                    }
                }
                else if(action == Action.GETPOS) {
                    if (item != null) {
                        item.SetSelected();
                        newPos = item.GetDiagonal(clickPoint);
                        action = Action.ZOOM;
                    }
                }
                else if(action == Action.ZOOM) {
                    item.Resize(newPos, clickPoint, -1);
                    item = null;
                    action = Action.INITZOOM;
                }
            }
        }
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

//    public void mousePressed(MouseEvent e) {
//        // TODO Auto-generated method stub
//        Component source = e.getComponent();
//        pressedPoint.setLocation(e.getX(), e.getY());
//        System.out.println("pressed point:" + pressedPoint);
//        if(source instanceof myCanvas)
//        {
//            //press left button
//            if(e.getButton() == MouseEvent.BUTTON1)
//            {
//                if(currentItem != null)
//                    currentItem.setNotChosed();
//
//                //no instruction, reselect current item
//                if(currentAction == Action.EMPTY)
//                {
//                    currentItem = myItemController.chooseItem(pressedPoint);
//                    if(currentItem != null)
//                        currentItem.setChosed();
//                }
//                //the first step in creation, set the first point, repaint
//                else if(currentAction == Action.CREATEINIT)
//                {
//                    if(createType.equals("TYPE_LINE"))
//                        currentItem = myItemController.createItem(createType, pressedPoint.getX(), pressedPoint.getY(), pressedPoint.getX(), pressedPoint.getY());
//                    else
//                        currentItem = myItemController.createItem(createType, pressedPoint.getX(), pressedPoint.getY(), 0, 0);
//
//                    currentItem.setChosed();
//                    createPoint = new Point2D.Double(pressedPoint.getX(),pressedPoint.getY());
//                    currentAction = Action.CREATE;
//                }
//                //the second step of creation, end the creation, return to its first step
//                else if(currentAction == Action.CREATE)
//                {
//                    currentItem.resize(createPoint, pressedPoint,false);
//                    currentItem = null;
//                    currentAction = Action.CREATEINIT;
//                }
//                //the first step of movement, choose item
//                else if(currentAction == Action.MOVEINIT)
//                {
//                    currentItem = myItemController.chooseItem(pressedPoint);
//                    if(currentItem != null)
//                    {
//                        currentItem.setChosed();
//                        currentAction = Action.MOVECONFIM;
//                    }
//                }
//                //the second step of movement, confirm item
//                else if(currentAction == Action.MOVECONFIM)
//                {
//                    Items tmp = myItemController.chooseItem(pressedPoint);
//                    if(tmp == currentItem)
//                    {
//                        currentItem.setChosed();
//                        currentAction = Action.MOVE;
//                    }
//                    else
//                    {
//                        currentItem = null;
//                        currentAction = Action.MOVEINIT;
//                    }
//                }
//                //the third step of movement, end movement, return to its first step
//                else if( currentAction == Action.MOVE)
//                {
//                    currentItem.move(pressedPoint);
//                    currentItem = null;
//                    currentAction = Action.MOVEINIT;
//                }
//                //the first step of scale, choose item
//                else if(currentAction == Action.SCALEINIT)
//                {
//                    currentItem = myItemController.chooseItem(pressedPoint);
//                    if(currentItem != null)
//                    {
//                        currentItem.setChosed();
//                        currentAction = Action.GETPOSITION;
//                    }
//                }
//                //the second step of scale, get the fixed corner
//                else if(currentAction == Action.GETPOSITION)
//                {
//                    currentItem.setChosed();
//                    resizePoint = currentItem.getOppositePoint(pressedPoint);
//                    currentAction = Action.SCALE;
//                }
//                //the third setp of scale, end scale, return to its first step
//                else if(currentAction == Action.SCALE)
//                {
//                    currentItem.resize(resizePoint, pressedPoint, false);
//                    currentItem = null;
//                    currentAction = Action.SCALEINIT;
//                }
//                pressedLeft = true;
//                source.repaint();
//            }
//            else if(e.getButton() == MouseEvent.BUTTON3)
//            {
//                //cancel creation
//                if(currentAction == Action.CREATE)
//                {
//                    myItemController.removeTopItem();
//                    currentAction = Action.CREATEINIT;
//                }
//                //changing chosen item
//                else if(currentAction == Action.EMPTY || currentAction == Action.MOVECONFIM || currentAction == Action.GETPOSITION)
//                {
//                    currentItem.setNotChosed();
//                    currentItem = myItemController.rechooseItem(pressedPoint, pressedLeft);
//                    if(currentItem != null)
//                        currentItem.setChosed();
//                }
//                pressedLeft = false;
//                source.repaint();
//            }
//            else {
//                pressedLeft = false;
//            }
//        }
//    }
