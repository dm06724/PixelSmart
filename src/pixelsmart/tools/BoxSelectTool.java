package pixelsmart.tools;

import java.awt.Rectangle;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.SetClipShapeCommand;
import pixelsmart.ui.ImagePanel;

public class BoxSelectTool extends ToolAdapter {

    int startX, startY;

    @Override
    public void startAction(final ImagePanel panel) {
        startX = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        startY = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);
    }

    @Override
    public void finishAction(final ImagePanel panel) {
        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);

        int minX = Math.min(startX, mx);
        int maxX = Math.max(startX, mx);
        int minY = Math.min(startY, my);
        int maxY = Math.max(startY, my);

        Rectangle clip = new Rectangle(minX, minY, maxX - minX, maxY - minY);

        SetClipShapeCommand command = new SetClipShapeCommand(clip);
        CommandList.getInstance().addCommand(command);
    }

}
