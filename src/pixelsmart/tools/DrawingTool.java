package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.Project;

public abstract class DrawingTool extends ToolAdapter {

    protected int getBrushSize() {
        return Project.getCurrent().getBrushSize();
    }

    protected Color getColor() {
        return Project.getCurrent().getPrimaryBrushColor();
    }
}