package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.Input;

public abstract class AbstractTool {

    protected final void update(ImagePanel panel) {
        if (Input.getAnyMouseButtonDown()) {
            startAction(panel);
        } else if (Input.getAnyMouseButton()) {
            updateAction(panel);
        } else if (Input.getAnyMouseButtonUp()) {
            finishAction(panel);
        }
    }

    public abstract void startAction(ImagePanel panel);

    public abstract void updateAction(ImagePanel panel);

    public abstract void finishAction(ImagePanel panel);
}
