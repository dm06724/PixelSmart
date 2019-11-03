package pixelsmart;

import java.awt.Color;
import java.io.File;

import javax.swing.JOptionPane;

public class Project {
    private static Project instance;
    private Image image;
    private Color primaryBrushColor = Color.BLACK;
    private Color secondaryBrushColor = Color.WHITE;
    private int brushSize = 20;
    private Tool tool;
    private String brushShape;

    private Project(int imageWidth, int imageHeight) {
        this.image = new Image(imageWidth, imageHeight);
        this.tool = new MoveTool();
    }

    public static Project getCurrent() {
        return instance;
    }

    public static synchronized Project createNew(int imageWidth, int imageHeight) {
        if (getCurrent() != null) {
            // Prompt to save current project
            int result = JOptionPane.showOptionDialog(MainWindow.getInstance(),
                    "Do you want to save your current project?", "Save Current Project?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            switch (result) {
            default:
            case JOptionPane.CLOSED_OPTION:
            case JOptionPane.CANCEL_OPTION:
                return getCurrent();
            case JOptionPane.YES_OPTION:
                if (!getCurrent().save(null))
                    return getCurrent();
                break;
            case JOptionPane.NO_OPTION:
                break;
            }
        }
        return instance = new Project(imageWidth, imageHeight);
    }

    protected void update() {
        if (tool != null) {
            if (Input.getMouseButtonDown(0)) {
                tool.startAction();
            } else if (Input.getMouseButton(0)) {
                tool.updateAction();
            } else if (Input.getMouseButtonUp(0)) {
                tool.finishAction();
            }
        }
    }

    public Image getImage() {
        return this.image;
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

    public String getBrushShape() {
        return brushShape;
    }

    public void setBrushShape(String x) {
        brushShape = x;
    }

    public boolean save(File file) {
        // TODO serialize all project data somehow
        // layers, brush size, brush colors, etc.

        // JFileChooser fileChooser = new JFileChooser();

        // int result = fileChooser.showSaveDialog(null);
        // if (result == JFileChooser.APPROVE_OPTION) {
        // File file = fileChooser.getSelectedFile();

        // return true;
        // } else {
        // return false;
        // }
        return false;
    }

    public static synchronized Project load(File file) {
        // TODO load project
        return null;
    }
}