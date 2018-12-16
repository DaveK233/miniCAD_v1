import java.awt.*;
import java.util.LinkedList;

class ControlPanel {
    private LinkedList<Items> itemList = new LinkedList<>();
    ControlPanel() {

    }
    public void paintAll(Graphics g) {
        for(Items it : itemList) {
            it.Draw(g);
        }
    }
}
