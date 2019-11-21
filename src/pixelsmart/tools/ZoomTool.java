package pixelsmart.tools;

import pixelsmart.ui.ImagePanel;

public class ZoomTool extends ToolAdapter {

	// Unlike the other tools, this is a system thing
	// and does not generally get added to the undo and redo list.
	// It is less like a command and more like a simple slider.
	// For now, keep it like a singleton that is not a tool.

	public void zoomIn() {
		ImagePanel.get().zoomIn();
	}

	public void zoomOut() {
		ImagePanel.get().zoomOut();
	}

	@Override
	public void finishAction(ImagePanel panel) {
		// TODO right click zoom out
		zoomIn();
	}
}
