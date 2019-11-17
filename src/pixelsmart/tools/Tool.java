package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public interface Tool {
	public void startAction(ImagePanel image);

	public void updateAction(ImagePanel image);

	public void finishAction(ImagePanel image);
}
