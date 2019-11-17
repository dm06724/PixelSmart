package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.Input;
import pixelsmart.ui.MainWindow;

public class ToolManager {

    private static ToolManager instance;
    private Color primaryBrushColor = Color.BLACK;
    private Color secondaryBrushColor = Color.WHITE;
    private int brushSize = 10;
    private Tool tool;

    private ToolManager() {
        this.tool = new PencilTool();
    }

    public static synchronized ToolManager getInstance() {
        if (instance == null) {
            instance = new ToolManager();
        }
        return instance;
    }

    public void update() {
        ImagePanel panel = MainWindow.getInstance().getPanel();
        if (panel != null && tool != null) {
            if (Input.getMouseButtonDown(0)) {
                tool.startAction(panel);
            } else if (Input.getMouseButton(0)) {
                tool.updateAction(panel);
            } else if (Input.getMouseButtonUp(0)) {
                tool.finishAction(panel);
            }
        }
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
}