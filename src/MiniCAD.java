public class MiniCAD {
    public static void main(String[] args) {
        ControlPanel controller = new ControlPanel();
        mActionListener listener = new mActionListener(controller);
        MainFrame mMainFrame = new MainFrame(listener, controller);
        listener.Frame(mMainFrame);
        controller.setMainFrame(mMainFrame);
    }
}
