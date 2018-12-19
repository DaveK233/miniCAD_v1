import javax.management.monitor.StringMonitor;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

class ControlPanel {
    private LinkedList<Items> itemList = new LinkedList<>();    // all items in a list
    private MainFrame frame;
    private Graphics2D graphics;
    private Color color = Color.BLACK;
    private float width = 1.0f;

    ControlPanel() {

    }

    public Items newItem(double x1, double y1, double x2, double y2, String op) {
        if ("LINE".equals(op)) {
            Items newLine = new Line(graphics, color, width, x1, y1, x2, y2);
            itemList.add(newLine);
            return newLine;
        } else if ("RECTANGLE".equals(op)) {
            Items newRectangle = new Rectangle(graphics, color, width, x1, y1, x2, y2);
            itemList.add(newRectangle);
            return newRectangle;
        } else if ("ELLIPSE".equals(op)) {
            Items newEllipse = new Ellipse(graphics, color, width, x1, y1, x2, y2);
            itemList.add(newEllipse);
            return newEllipse;
        } else if ("FILLREC".equals(op)) {
            Items newFillRec = new FillRec(graphics, color, width, x1, y1, x2, y2);
            itemList.add(newFillRec);
            return newFillRec;
        } else if ("FILLELI".equals(op)) {
            Items newFillEli = new FillEli(graphics, color, width, x1, y1, x2, y2);
            itemList.add(newFillEli);
            return newFillEli;
        } else if ("TEXT".equals(op)) {
            Items newText = new Text(graphics, color, null, width, x1, y1, x2, y2);
            itemList.add(newText);
            return newText;
        }
        return null;
    }

    public Items newItem(int[] XArray, int[] YArray, String op) {
        if ("POLYLINE".equals(op)) {
            Items newPolyline = new Polyline(graphics, color, width, XArray, YArray);
            itemList.add(newPolyline);
            return newPolyline;
        } else if ("POLYGON".equals(op)) {
            Items newPolygon = new Polygon(graphics, color, width, XArray, YArray);
            itemList.add(newPolygon);
            return newPolygon;
        }
        return null;
    }

    public Items Select(Point2D point) {
        Items t = null;
        for(Items it : itemList) {
            if(it.InsideJudge(point)) {
                t = it;
                break;
            }
        }
        return t;
    }

    public Items Select2(Point2D point, boolean lastLeft) {
        Items t = Select(point);
        if(t != null) {
            itemList.remove(t);
            itemList.add(t);
        }
        if(lastLeft) {
            t = Select(point);
            if(t != null) {
                itemList.remove(t);
                itemList.add(t);
            }
        }
        return t;
    }

    public void remove(Items item) {
        for(Items it : itemList) {
            if(item == it) {
                itemList.remove(it);
                break;
            }
        }
    }

    public void listPop() {
        itemList.remove(itemList.size()-1);
    }

    public void SetColor(Color icolor) {
        color = icolor;
    }

    public void setMainFrame(MainFrame f)
    {
        frame = f;
        graphics = frame.get2Dframe();
    }

    public void paintAll(Graphics g) {
        for(Items it : itemList) {
            it.Draw(g);
        }
    }

    public boolean Load() throws IOException {
        FileDialog loadDialog = new FileDialog(frame, "Load from", FileDialog.LOAD);
        loadDialog.setVisible(true);
        String path = loadDialog.getDirectory();
        String fname = loadDialog.getFile();
        if(fname == null || path == null) {
            return false;
        }
        else {
            itemList.clear();   // clean current items
            String str = null;
            String fullPath = path + fname;
            File file = new File(fullPath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((str = br.readLine()) != null) {
                String[] arr = str.split(",");
                color = new Color(Integer.parseInt(arr[1]));
                width = Float.parseFloat(arr[2]);
                String textString = arr[3];
                if(arr[0].equals("TEXT")) {
                    Items newText = new Text(graphics, color, textString, width, Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7]));
                    itemList.add(newText);
                }
                else if(arr[0].equals("POLYLINE")) {
                    int pointNum = Integer.parseInt(arr[3]);
                    ArrayList<Integer> tempXList = new ArrayList<>();
                    ArrayList<Integer> tempYList = new ArrayList<>();
                    for(int i = 4; i < (4+pointNum); i++) {
                        tempXList.add(Integer.valueOf(arr[i]));
                    }
                    for(int i = (4 + pointNum); i < (4 + (2 * pointNum)); i++) {
                        tempYList.add(Integer.valueOf(arr[i]));
                    }
                    int[] tempXArray = new int[tempXList.size()];
                    int[] tempYArray = new int[tempYList.size()];
                    for(int i = 0; i < pointNum; i++) {
                        tempXArray[i] = tempXList.get(i);
                        tempYArray[i] = tempYList.get(i);
                    }
                    Items newPolyline = new Polyline(graphics, color, width, tempXArray, tempYArray);
                    itemList.add(newPolyline);
                }
                else {
                    newItem(Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), arr[0]);
                }
            }
            br.close();
        }
        return true;
    }

    public void SaveTo() throws IOException {
        FileDialog saveDialog = new FileDialog(frame, "Save As", FileDialog.SAVE);
        saveDialog.setVisible(true);
        String path = saveDialog.getDirectory();
        String fname = saveDialog.getFile();
        if(path == null || fname == null) {
            JOptionPane.showMessageDialog(null, "Warning: Please Set File Name and Path!", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            String fullPath = path + fname;
            File file = new File(fullPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            for(Items it : itemList) {
                bw.write(it.ReadItem());
            }
            bw.close();
        }
    }
}
