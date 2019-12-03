package pixelsmart.tools;

import java.awt.Color;
import java.awt.Graphics2D;

import pixelsmart.image.Layer;

public abstract class DrawingTool extends ToolAdapter {

	public Layer layerAppliedTo = null;
	
    protected int getBrushSize() {
        return ToolManager.getInstance().getBrushSize();
    }

    protected Color getColor() {
        return ToolManager.getInstance().getPrimaryBrushColor();
    }

    protected Color getSecondaryColor(){
        return ToolManager.getInstance().getSecondaryBrushColor();
    }
    
    public abstract void drawTemporaryImage(Graphics2D g);
}