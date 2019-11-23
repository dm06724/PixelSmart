package pixelsmart.tools;

import java.awt.geom.Path2D;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.SetClipShapeCommand;
import pixelsmart.ui.ImagePanel;

public class LassoTool extends AbstractTool {
    private final Path2D.Double clipShape = new Path2D.Double();

    @Override
    public void startAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);

        clipShape.reset();
        clipShape.moveTo(mx, my);
    }

    @Override
    public void updateAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);

        clipShape.lineTo(mx, my);
    }

    @Override
    public void finishAction(final ImagePanel panel) {
        clipShape.closePath();

        SetClipShapeCommand command = new SetClipShapeCommand(panel, clipShape);
        CommandList.getInstance().addCommand(command);
    }
}