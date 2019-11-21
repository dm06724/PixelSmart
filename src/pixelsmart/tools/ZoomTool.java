package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public class ZoomTool extends ToolAdapter {

	@Override
	public void finishAction(ImagePanel panel) {
		panel.zoomIn();
	}
}
