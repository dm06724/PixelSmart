package pixelsmart.tools;

import java.awt.Rectangle;

import pixelsmart.ui.ImagePanel;

public class BoxSelectTool implements Tool {

	int startX, startY;

	@Override
	public void startAction(final ImagePanel panel) {
		startX = panel.getMouseX();
		startY = panel.getMouseY();
	}

	@Override
	public void updateAction(final ImagePanel panel) {
	}

	@Override
	public void finishAction(final ImagePanel panel) {
		int mx = panel.getMouseX();
		int my = panel.getMouseY();

		int minX = Math.min(startX, mx);
		int maxX = Math.max(startX, mx);
		int minY = Math.min(startY, my);
		int maxY = Math.max(startY, my);

		Rectangle clip = new Rectangle(minX, minY, maxX - minX, maxY - minY);

		panel.setClip(clip);
	}

}
