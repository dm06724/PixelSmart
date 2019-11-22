package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public interface Tool {
	public void startAction(ImagePanel panel);

	public void updateAction(ImagePanel panel);

	public void finishAction(ImagePanel panel);
}
