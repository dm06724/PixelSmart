package pixelsmart.tools;

import pixelsmart.Project;
import pixelsmart.image.Image;

public class ZoomTool implements Tool {

	// Unlike the other tools, this is a system thing
	// and does not generally get added to the undo and redo list.
	// It is less like a command and more like a simple slider.
	// For now, keep it like a singleton that is not a tool.

	public void zoomIn() {
		Project.getCurrent().setZoomLevel(Project.getCurrent().getZoomLevel() * 2);
	}

	public void zoomOut() {
		Project.getCurrent().setZoomLevel(Project.getCurrent().getZoomLevel() / 2);
	}

	@Override
	public void startAction(Image image) {
		// TODO Auto-generated method stub
		zoomIn();
	}

	@Override
	public void updateAction(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishAction(Image image) {
		// TODO Auto-generated method stub

	}
}
