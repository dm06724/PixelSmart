package pixelsmart.tools;

import java.awt.Color;

public abstract class DrawingTool extends ToolAdapter {

    protected int getBrushSize() {
        return ToolManager.getInstance().getBrushSize();
    }

    protected Color getColor() {
        return ToolManager.getInstance().getPrimaryBrushColor();
    }

    protected Color getSecondaryColor(){
        return ToolManager.getInstance().getSecondaryBrushColor();
    }
}