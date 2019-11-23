package pixelsmart.tools;

import java.awt.Rectangle;

import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class MoveTool extends ToolAdapter {
    private static final int MAX_SNAP_DISTANCE = 5;

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

        // TODO: Snaps to other layers well, but does not snap to image.
        // Just need to check with 0,0 and image.getWidth() and image.getHeight()
        // Draw purple lines where it is wanting to snap to

        final int lx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE) + mouseOffsetX;
        final int ly = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE) + mouseOffsetY;

        int snapX = lx;
        int snapY = ly;

        final Rectangle rect = layer.getRect();
        final int maxX = lx + rect.width;
        final int maxY = ly + rect.height;
        int dist;
        int closestX = Integer.MAX_VALUE;
        int closestY = Integer.MAX_VALUE;
        for (Layer l : panel.getImage()) {
            if (layer == l)
                continue;

            final Rectangle otherRect = l.getRect();
            final int otherMaxX = otherRect.x + otherRect.width;
            final int otherMaxY = otherRect.y + otherRect.height;

            // Test X, X
            dist = Math.abs(lx - otherRect.x);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestX) {
                snapX = otherRect.x;
                closestX = dist;
            }

            // Test X, Width
            dist = Math.abs(lx - otherMaxX);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestX) {
                snapX = otherMaxX;
                closestX = dist;
            }

            // Test Max X, X
            dist = Math.abs(maxX - otherRect.x);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestX) {
                snapX = otherRect.x - rect.width;
                closestX = dist;
            }

            // Test Width, Width
            dist = Math.abs(maxX - otherMaxX);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestX) {
                snapX = otherMaxX - rect.width;
                closestX = dist;
            }

            // Test Y, Y
            dist = Math.abs(ly - otherRect.y);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestY) {
                snapY = otherRect.y;
                closestY = dist;
            }

            // Test Y, Height
            dist = Math.abs(ly - otherMaxY);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestY) {
                snapY = otherMaxY;
                closestY = dist;
            }

            // Test Height, Y
            dist = Math.abs(maxY - otherRect.y);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestY) {
                snapY = otherRect.y - rect.height;
                closestY = dist;
            }

            // Test Height, Height
            dist = Math.abs(maxY - otherMaxY);
            if (dist <= MAX_SNAP_DISTANCE && dist < closestY) {
                snapY = otherMaxY - rect.width;
                closestY = dist;
            }
        }

        layer.setPosition(snapX, snapY);
    }
}