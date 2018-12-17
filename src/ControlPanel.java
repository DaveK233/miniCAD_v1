import java.awt.*;
import java.awt.geom.Point2D;
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
        switch (op) {
            case "LINE":
                Items newLine = new Line(graphics, color, width, x1, y1, x2, y2);
                itemList.add(newLine);
                return newLine;
            case "RECTANGLE":
                Items newRectangle = new Rectangle(graphics, color, width, x1, y1, x2, y2);
                itemList.add(newRectangle);
                return newRectangle;
            case "ELLIPSE":
                Items newEllipse = new Ellipse(graphics, color, width, x1, y1, x2, y2);
                itemList.add(newEllipse);
                return newEllipse;
            case "TEXT":
                Items newText = new Text(graphics, color, null, width, x1, y1, x2, y2);
                itemList.add(newText);
                return newText;
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
}

//    public boolean loadItems() throws IOException
//    {
//        FileDialog openDia = new FileDialog(mainFrame, "Open", FileDialog.LOAD);
//        openDia.setVisible(true);
//        String dirPath = openDia.getDirectory();
//        String fileName = openDia.getFile();
//        System.out.println(dirPath + fileName);
//        if (dirPath == null || fileName == null)//判断路径和文件是否为空
//            return false;
//        else
//        {
//            itemList.clear();
//            String str = null;
//            File file = new File(dirPath + fileName);
//            BufferedReader bReader = new BufferedReader(new FileReader(file));
//            while((str = bReader.readLine()) != null)
//            {
//                System.out.println(str);
//                String [] arr = str.split(",");
//                t_color = new Color(Integer.parseInt(arr[1]));
//                t_width = Float.parseFloat(arr[2]);
//                if(arr[0].equals("TYPE_TEXT"))
//                {
//                    Items text = new ItemText(t_g2d, arr[3], t_color, Float.parseFloat(arr[2]),  Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]), Integer.parseInt(arr[7]));
//                    itemList.add(text);
//                }
//                else
//                    createItem(arr[0], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));
//            }
//            bReader.close();
//        }
//        return true;
//    }
//    /**
//     * Description	: save items into a file
//     * @throws IOException
//     */
//    public void saveItems() throws IOException
//    {
//        if(itemList.size() == 0)
//            JOptionPane.showMessageDialog(null, "Warning: the canva is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
//        else
//        {
//            FileDialog saveDia = new FileDialog(mainFrame, "Save", FileDialog.SAVE);;
//            saveDia.setVisible(true);
//            String dirPath = saveDia.getDirectory();
//            String fileName = saveDia.getFile();
//            if (dirPath == null || fileName == null)//判断路径和文件是否为空
//                return;
//            else
//            {
//                File file = new File(dirPath + fileName);
//                FileWriter fWriter = new FileWriter(file, false);
//                BufferedWriter bWriter = new BufferedWriter(fWriter);
//                for(Items t: itemList)
//                {
//                    bWriter.write(t.getInfo());
//                }
//                bWriter.close();
//                fWriter.close();
//
//            }
//        }
//    }
//
//}
