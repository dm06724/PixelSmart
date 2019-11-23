package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public class DebugTool extends AbstractTool {

    @Override
    public void startAction(ImagePanel panel) {
        System.out.println(panel);
    }

    @Override
    public void updateAction(ImagePanel panel) {

    }

    @Override
    public void finishAction(ImagePanel panel) {
    }
}