package pixelsmart.tools;

import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class MoveTool extends ToolAdapter {

    private int mouseOffsetX, mouseOffsetY;

    @Override
    public void startAction(final ImagePanel panel) {
        final Layer layer = panel.getActiveLayer();
        final int layerX = layer.getX();
        final int layerY = layer.getY();

        mouseOffsetX = layerX - panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        mouseOffsetY = layerY - panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);
    }

    @Override
    public void updateAction(final ImagePanel panel) {
        final Layer layer = panel.getActiveLayer();

        final int lx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE) + mouseOffsetX;
        final int ly = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE) + mouseOffsetY;

        layer.setPosition(lx, ly);
    }
}