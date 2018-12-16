import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class MainFrame extends JFrame {
    private mActionListener mListener;
    private ControlPanel mControl;
    private mCanvas canvas;
    private mToolbar toolbar;
    private mMenu menu;

    public MainFrame(mActionListener listener, ControlPanel controller) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        mControl = controller;
        mListener = listener;
        canvas = new mCanvas(mListener, mControl);

    }
}

class mMenu extends JMenuBar {

}

class mToolbar extends JToolBar {

}

class mCanvas extends JPanel {
    private mActionListener mListener;
    private ControlPanel mControl;
    public mCanvas(mActionListener listener, ControlPanel controller) {
        this.mListener = listener;
        this.mControl = controller;
        this.setBackground(Color.WHITE);
        this.addMouseListener(mListener);
        this.addKeyListener(mListener);
        this.addMouseMotionListener(mListener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.mControl.paintAll(g);
    }
}
