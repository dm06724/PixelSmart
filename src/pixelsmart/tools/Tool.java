package pixelsmart.tools;

import pixelsmart.image.Image;

public interface Tool {
	public void startAction(Image image);

	public void updateAction(Image image);

	public void finishAction(Image image);
}
