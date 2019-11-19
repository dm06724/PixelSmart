package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public class DebugTool implements Tool {

    @Override
    public void startAction(ImagePanel image) {
        System.out.println(image);
    }

    @Override
    public void updateAction(ImagePanel image) {

    }

    @Override
    public void finishAction(ImagePanel image) {
    }

}