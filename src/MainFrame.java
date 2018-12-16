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
        menu = new mMenu(mListener);
        toolbar = new mToolbar(mListener);
        this.setTitle("MiniCAD");
        Dimension frameSize = new Dimension(600, 600);
        this.setMinimumSize(frameSize);
        int windowHeight = (int) (screen.getHeight() / 2);
        int windowWidth = (int) (screen.getWidth() / 2);
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(listener);
        this.setJMenuBar(menu);
        this.setLayout(new BorderLayout(5, 5));
        this.add(toolbar, BorderLayout.PAGE_START);
        this.add(canvas, BorderLayout.CENTER);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void frameFocus() {
        canvas.requestFocus();
    }

    public Graphics2D get2Dframe() {
        return (Graphics2D)canvas.getGraphics();
    }

    public void frameRefresh() {
        canvas.repaint();
    }
}

class mMenu extends JMenuBar {
    private JMenu file, item, help;
    private JMenuItem openFrom, saveAs; // file menu options
    private JMenuItem drawLine, drawRec, drawEli, addText, zoomItem, moveItem, color;   // Item menu options
    private JMenuItem about, itemHelp;  // Help menu options
    private mActionListener mListener;
    public mMenu(mActionListener listener) {
        mListener = listener;
        file = new JMenu("File");
        item = new JMenu("Item");
        help = new JMenu("Help");

        openFrom = new JMenuItem("Open From...");
        openFrom.setActionCommand("OPEN");
        openFrom.addActionListener(mListener);
        file.add(openFrom);

        saveAs = new JMenuItem("Save As...");
        saveAs.setActionCommand("SAVE");
        saveAs.addActionListener(mListener);
        file.add(saveAs);

        drawLine = new JMenuItem("Draw Line");
        drawLine.setActionCommand("LINE");
        drawLine.addActionListener(mListener);
        item.add(openFrom);

        drawRec = new JMenuItem("Draw Rectangle");
        drawRec.setActionCommand("RECTANGLE");
        drawRec.addActionListener(mListener);
        item.add(drawRec);

        drawEli = new JMenuItem("Draw Ellipse");
        drawEli.setActionCommand("ELLIPSE");
        drawEli.addActionListener(mListener);
        item.add(drawEli);

        addText = new JMenuItem("Add Text");
        addText.setActionCommand("TEXT");
        addText.addActionListener(mListener);
        item.add(addText);

        zoomItem = new JMenuItem("Zoom in/out");
        zoomItem.setActionCommand("ZOOM");
        zoomItem.addActionListener(mListener);
        item.add(zoomItem);

        moveItem = new JMenuItem("Move Item");
        moveItem.setActionCommand("MOVE");
        moveItem.addActionListener(mListener);
        item.add(moveItem);

        color = new JMenuItem("Set Color");
        color.setActionCommand("COLOR");
        color.addActionListener(mListener);
        item.add(color);

        about = new JMenuItem("About");
        about.setActionCommand("ABOUT");
        about.addActionListener(mListener);
        help.add(about);

        itemHelp = new JMenuItem("Help");
        itemHelp.setActionCommand("HELP");
        itemHelp.addActionListener(mListener);
        help.add(itemHelp);

        this.add(file);
        this.add(item);
        this.add(help);
        Dimension menuSize = new Dimension(600,40);
        this.setPreferredSize(menuSize);
    }
}

class mToolbar extends JToolBar {
    private ImageIcon openIcon, saveIcon, lineIcon, rectangleIcon, ellipseIcon, textIcon, selectIcon, zoomIcon, moveIcon, colorIcon;
    private JButton openBtn, saveBtn, lineBtn, rectangleBtn, ellipseBtn, textBtn, selectBtn, zoomBtn, moveBtn, colorBtn;
    private mActionListener mListener;
    public mToolbar(mActionListener listener) {
        mListener = listener;
        saveIcon = new ImageIcon(new ImageIcon("icons/save.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        openIcon = new ImageIcon(new ImageIcon("icons/open.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        lineIcon = new ImageIcon(new ImageIcon("icons/drawline.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        rectangleIcon = new ImageIcon(new ImageIcon("icons/rectangle.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        ellipseIcon = new ImageIcon(new ImageIcon("icons/elipse.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        textIcon = new ImageIcon(new ImageIcon("icons/textfield.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        selectIcon = new ImageIcon(new ImageIcon("icons/select.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        zoomIcon = new ImageIcon(new ImageIcon("icons/zoom.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        moveIcon = new ImageIcon(new ImageIcon("icons/move.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));
        colorIcon = new ImageIcon(new ImageIcon("icons/color.png").getImage().getScaledInstance(32, 32, Image.SCALE_FAST));

        saveBtn = new JButton(saveIcon);
        saveBtn.setActionCommand("SAVE");
        saveBtn.addActionListener(mListener);

        openBtn = new JButton(openIcon);
        openBtn.setActionCommand("OPEN");
        openBtn.addActionListener(mListener);

        selectBtn = new JButton(selectIcon);
        selectBtn.setActionCommand("SELECT");
        selectBtn.addActionListener(mListener);

        lineBtn = new JButton(lineIcon);
        lineBtn.setActionCommand("LINE");
        lineBtn.addActionListener(mListener);

        rectangleBtn = new JButton(rectangleIcon);
        rectangleBtn.setActionCommand("RECTANGLE");
        rectangleBtn.addActionListener(mListener);

        ellipseBtn = new JButton(ellipseIcon);
        ellipseBtn.setActionCommand("ELLIPSE");
        ellipseBtn.addActionListener(mListener);

        textBtn = new JButton(textIcon);
        textBtn.setActionCommand("TEXT");
        textBtn.addActionListener(mListener);

        zoomBtn = new JButton(zoomIcon);
        zoomBtn.setActionCommand("ZOOM");
        zoomBtn.addActionListener(mListener);

        moveBtn = new JButton(moveIcon);
        moveBtn.setActionCommand("MOVE");
        moveBtn.addActionListener(mListener);

        colorBtn = new JButton(colorIcon);
        colorBtn.setActionCommand("COLOR");
        colorBtn.addActionListener(mListener);

        this.add(openBtn);
        this.add(saveBtn);
        this.add(selectBtn);
        this.add(zoomBtn);
        this.add(moveBtn);
        this.add(colorBtn);
        this.add(rectangleBtn);
        this.add(ellipseBtn);
        this.add(lineBtn);
        this.setAlignmentX(0);
    }
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
