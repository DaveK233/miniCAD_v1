import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class mActionListener implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    public enum Action{EMPTY, INITPOLY, NEWPOLY, INITNEW, NEW, INITZOOM, ZOOM, GETPOS, INITMOV, TOMOVE, MOV}
    private ControlPanel controller;
    private MainFrame mainFrame;
    private Action action;
    private Items item;
    private String opNew;
    private static Point2D clickPoint = new Point2D.Double();
    private Point2D initPos;
    private Point2D newPos;
    private Point2D tempClickPoint;
    private boolean leftClicked;
    private int nlist;
    private Color color;
    private ArrayList<Point2D> pointList = new ArrayList<>();

    public mActionListener(ControlPanel cont) {
        controller = cont;
        action = Action.EMPTY;
    }

    public void Frame(MainFrame frame) {
        mainFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(e.getActionCommand().equals("OPEN")) {
            try {
                if(controller.Load()) {
                    mainFrame.repaint();
                }
                else {
                    action = Action.EMPTY;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("SAVE")) {
            try {
                controller.SaveTo();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("ABOUT")) {
            JOptionPane.showMessageDialog(null, "By Jin Dawei", "About", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getActionCommand().equals("HELP")) {
            JOptionPane.showMessageDialog(null, "Help", "Help", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getActionCommand().equals("COLOR")) {
            color = JColorChooser.showDialog((Component) obj, "Color", Color.BLACK);
            if(item != null) {
                item.SetColor(color);
            }
            else {
                controller.SetColor(color);
            }
            action = Action.EMPTY;
        }
        else {
            if(item != null) {
                item.SetSelected();
            }
            if(e.getActionCommand().equals("LINE") || e.getActionCommand().equals("RECTANGLE") || e.getActionCommand().equals("FILLREC") || e.getActionCommand().equals("FILLELI") ||
                    e.getActionCommand().equals("ELLIPSE") || e.getActionCommand().equals("TEXT")) {
                opNew = e.getActionCommand();
                action = Action.INITNEW;
            }
            else if(e.getActionCommand().equals("POLYLINE") || e.getActionCommand().equals("POLYGON")) {
                opNew = e.getActionCommand();
                action = Action.INITPOLY;
            }
            else if(e.getActionCommand().equals("MOVE")) {
                action = Action.INITMOV;
            }
            else if(e.getActionCommand().equals("ZOOM")) {
                action = Action.ZOOM;
            }
            else if(e.getActionCommand().equals("SELECT")) {
                action = Action.EMPTY;
            }
        }
        mainFrame.repaint();
        mainFrame.frameFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Component comp = e.getComponent();
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(item.select) {
                item.Zoom(true);    // zoom in
            }
            comp.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(item.select) {
                item.Zoom(false);   // zoom out
            }
            comp.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(item.select) {
                item.ChageStroke(true);
            }
            comp.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(item.select) {
                item.ChageStroke(false);
            }
            comp.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D) {
            if(item != null) {
                controller.remove(item);
            }
            action = Action.EMPTY;
            comp.repaint();
        }
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
                else if(action == Action.INITPOLY || action == Action.NEWPOLY) {
                    if(opNew.equals("POLYLINE") || opNew.equals("POLYGON")) {
                        tempClickPoint = new Point2D.Double(clickPoint.getX(), clickPoint.getY());
                        pointList.add(tempClickPoint);
                    }
                    action = Action.NEWPOLY;
                }
                else if(action == Action.INITNEW) {
                    if(opNew.equals("LINE")) {
                        item = controller.newItem(clickPoint.getX(), clickPoint.getY(), clickPoint.getX(), clickPoint.getY(), opNew);
                        item.SetSelected();
                        initPos = new Point2D.Double(clickPoint.getX(), clickPoint.getY());
                        action = Action.NEW;
                    }
                    else {
                        item = controller.newItem(clickPoint.getX(), clickPoint.getY(), 0, 0, opNew);
                        item.SetSelected();
                        initPos = new Point2D.Double(clickPoint.getX(), clickPoint.getY());
                        action = Action.NEW;
                    }
                }
                else if(action == Action.NEW) {
                    if (item != null) {
                        item.Resize(initPos, clickPoint, -1);
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
                    if (item != null) {
                        item.Resize(newPos, clickPoint, -1);
                    }
                    item = null;
                    action = Action.INITZOOM;
                }
                leftClicked = true;
                comp.repaint();
            }
            // right click
            else if(e.getButton() == MouseEvent.BUTTON3) {
                if(action == Action.NEW) {
                    controller.listPop();
                    action = Action.INITNEW;
                }
                else if(action == Action.NEWPOLY) {
//                    tempClickPoint = new Point2D.Double(clickPoint.getX(), clickPoint.getY());
//                    pointList.add(tempClickPoint);
                    nlist = pointList.size();
                    int[] xList = new int[nlist];
                    int[] yList = new int[nlist];
                    for(int i = 0; i < nlist; i++) {
                        xList[i] = (int) pointList.get(i).getX();
                        yList[i] = (int) pointList.get(i).getY();
                    }
                    item = controller.newItem(xList, yList, opNew);
                    item.SetSelected();
                    comp.repaint();
                    pointList = new ArrayList<>();
                    action = Action.INITPOLY;
                }
                else if(action == Action.GETPOS || action == Action.EMPTY || action == Action.TOMOVE) {
                    item.UnSelected();
                    item = controller.Select2(clickPoint, leftClicked);
                    if(item != null) {
                        item.SetSelected();
                    }
                    leftClicked = false;
                    comp.repaint();
                }
            }
            else {
                leftClicked = false;
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
        Component comp = e.getComponent();
        Point2D movedPoint = new Point2D.Double(e.getX(), e.getY());
        if(comp instanceof mCanvas) {
            if(action == Action.NEW) {
                item.Resize(initPos, movedPoint, -1);
                comp.repaint();
            }
            else if(action == Action.ZOOM) {
                item.Resize(newPos, movedPoint, -1);
                comp.repaint();
            }
            else if(action == Action.MOV) {
                item.Move(movedPoint);
                comp.repaint();
            }
        }
    }
}