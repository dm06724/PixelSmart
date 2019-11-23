package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.ui.ImagePanel;

public class ToolManager {

    private static ToolManager instance;
    private Color primaryBrushColor = Color.BLACK;
    private Color secondaryBrushColor = Color.WHITE;
    private int brushSize = 10;
    private AbstractTool tool;

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
        if (panel.getImage() != null && tool != null) {
            tool.update(panel);
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

    public void setTool(AbstractTool tool) {
        this.tool = tool;
    }

    public AbstractTool getTool() {
        return this.tool;
    }
}