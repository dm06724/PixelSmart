package pixelsmart.tools;

import java.awt.geom.Path2D;

import pixelsmart.ui.ImagePanel;

public class LassoTool implements Tool {
    private Path2D.Double finalStrokeShape;

    @Override
    public void startAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        finalStrokeShape = new Path2D.Double();
        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);
        finalStrokeShape.moveTo(mx, my);
    }

    @Override
    public void updateAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);

        finalStrokeShape.lineTo(mx, my);
    }

    @Override
    public void finishAction(final ImagePanel panel) {
        finalStrokeShape.closePath();
        panel.setClip(finalStrokeShape);
    }
}