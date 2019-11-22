package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.Input;

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
        ImagePanel panel = ImagePanel.get();
        if (tool != null) {
            if (Input.getMouseButtonDown(Input.LEFT_MOUSE)) {
                tool.startAction(panel);
            } else if (Input.getMouseButton(Input.LEFT_MOUSE)) {
                tool.updateAction(panel);
            } else if (Input.getMouseButtonUp(Input.LEFT_MOUSE)) {
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