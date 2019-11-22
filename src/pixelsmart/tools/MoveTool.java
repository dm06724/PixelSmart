package pixelsmart.tools;

import java.awt.Rectangle;

import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class MoveTool extends ToolAdapter {
    private static final int SNAP_DISTANCE = 15;

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

        // TODO Snapping
        // Rectangle rect = panel.getLayerRect(layer);
        // int closestX = Integer.MAX_VALUE, closestY = Integer.MAX_VALUE;
        // int x = lx, y = ly;
        // for (Layer l : panel.getImage()) {
        // Rectangle otherRect = panel.getLayerRect(l);
        // int dist;
        // if ((dist = Math.abs(lx - otherRect.x)) < closestX && dist < SNAP_DISTANCE) {
        // x = otherRect.x;
        // closestX = dist;
        // } else if ((dist = Math.abs((lx + rect.width) - (otherRect.x +
        // otherRect.width))) < closestX
        // && dist < SNAP_DISTANCE) {
        // x = otherRect.x + otherRect.width - rect.width;
        // closestX = dist;
        // }
        // if ((dist = Math.abs(ly - otherRect.y)) < closestY && dist < SNAP_DISTANCE) {
        // y = otherRect.x;
        // closestY = dist;
        // } else if ((dist = Math.abs((ly + rect.height) - (otherRect.y +
        // otherRect.height))) < closestY
        // && dist < SNAP_DISTANCE) {
        // y = otherRect.y + otherRect.height - rect.height;
        // closestY = dist;
        // }
        // }

        int snapX = lx;
        int snapY = ly;

        for (Layer l : panel.getImage()){
            
        }

        layer.setPosition(snapX, snapY);
    }
}