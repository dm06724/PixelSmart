package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.Input;

public class ZoomTool extends ToolAdapter {

    @Override
    public void finishAction(ImagePanel panel) {
        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_IMAGE);
        if (Input.getMouseButtonUp(Input.LEFT_MOUSE))
            panel.zoomInAround(mx, my);
        else if (Input.getMouseButtonUp(Input.RIGHT_MOUSE))
            panel.zoomOutAround(mx, my);
    }
}
