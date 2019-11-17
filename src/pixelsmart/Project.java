package pixelsmart;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pixelsmart.image.Image;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.Tool;

public class Project {
    private static final double MIN_ZOOM = 0.2;
    private static final double MAX_ZOOM = 5.0;
    private static Project instance;
    private Color primaryBrushColor = Color.BLACK;
    private Color secondaryBrushColor = Color.WHITE;
    private int brushSize = 10;
    private Tool tool;
    private double zoomLevel = 1;

    private Project(int imageWidth, int imageHeight) {
        MainWindow.getInstance().getPanel().setImage(new Image(imageWidth, imageHeight));
        this.tool = new PencilTool();
    }

    public static Project getCurrent() {
        return instance;
    }

    public static synchronized Project createNew(int imageWidth, int imageHeight) {
        return instance = new Project(imageWidth, imageHeight);
    }

    protected void update() {
        if (tool != null) {
            if (Input.getMouseButtonDown(0)) {
                // System.out.println("button down");
                tool.startAction(getImage());
            } else if (Input.getMouseButton(0)) {
                // System.out.println("button held");
                tool.updateAction(getImage());
            } else if (Input.getMouseButtonUp(0)) {
                // System.out.println("button up");
                tool.finishAction(getImage());
            }
        }
    }

    public Image getImage() {
        return MainWindow.getInstance().getPanel().getImage();
    }

    public Color getPrimaryBrushColor() {
        return this.primaryBrushColor;
    }

    public Color getSecondaryBrushColor() {
        return this.secondaryBrushColor;
    }

    public int getBrushSize() {
        return this.brushSize;
    }

    public void setPrimaryBrushColor(Color color) {
        this.primaryBrushColor = color;
    }

    public void setSecondaryBrushColor(Color color) {
        this.secondaryBrushColor = color;
    }

    public void setBrushSize(int size) {
        this.brushSize = size;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tool getTool() {
        return this.tool;
    }

    private void addToArrayList(int size, int value, ArrayList<Byte> list) {
        if (size <= 4) {
            for (int i = 0; i < size; i++) {
                list.add((byte) ((value >> 8 * i) & 0xFF));
            }
        }

    }

    public void setZoomLevel(double level) {
        zoomLevel = MathUtil.clamp(level, MIN_ZOOM, MAX_ZOOM);
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    private ArrayList<Byte> getFileHeader() {
        // must store overall Width, Height, Amount of Layers, Current Brush, Current
        // Color,
        // Active Layer
        ArrayList<Byte> head = new ArrayList<Byte>();
        head.add((byte) 'p');
        head.add((byte) 's');

        // addToArrayList(4, image.getWidth(), head);
        // addToArrayList(4, image.getHeight(), head);
        // addToArrayList(2, image.getLayers().size(), head);

        return head;
    }

    private void writeFileHeader(FileOutputStream f) {
        try {
            ArrayList<Byte> values = getFileHeader();
            byte[] convertedValues = new byte[values.size()];
            for (int i = 0; i < values.size(); i++) {
                convertedValues[i] = values.get(i);
            }

            f.write(convertedValues);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public boolean save(File file) {
        // TODO serialize all project data somehow
        // layers, brush size, brush colors, etc.
        FileOutputStream fileWriter = null;

        try {
            fileWriter = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (fileWriter != null) {
            writeFileHeader(fileWriter);

            try {
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }

        // JFileChooser fileChooser = new JFileChooser();

        // int result = fileChooser.showSaveDialog(null);
        // if (result == JFileChooser.APPROVE_OPTION) {
        // File file = fileChooser.getSelectedFile();

        // return true;
        // } else {
        // return false;
        // }
        // return false;
    }

    public static synchronized Project load(File file) {
        // TODO load project
        return null;
    }
}