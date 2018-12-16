import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private mActionListener mListener;
    private ControlPanel mControl;
    private mCanvas canvas;
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
