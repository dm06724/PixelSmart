package pixelsmart.tools;

import pixelsmart.Input;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class MoveTool extends ToolAdapter {

    private int mouseOffsetX, mouseOffsetY;

    @Override
    public void startAction(final Image image) {
        final Layer layer = image.getActiveLayer();
        final int layerX = layer.getX();
        final int layerY = layer.getY();

        mouseOffsetX = layerX - Input.getMouseX();
        mouseOffsetY = layerY - Input.getMouseY();
    }

    @Override
    public void updateAction(final Image image) {
        final Layer layer = image.getActiveLayer();

        final int lx = Input.getMouseX() + mouseOffsetX;
        final int ly = Input.getMouseY() + mouseOffsetY;

        layer.setPosition(lx, ly);
    }
}