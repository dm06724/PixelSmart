package pixelsmart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class PencilTool implements Tool {

	// should be able to keep track of its size, color, and where it gets applied
	// must maintain the image before it was modified so it can undo.

	// TODO make these fields part of the project class somehow
	private Color color;
	private int size = 1;

	private int lastMouseX = -1, lastMouseY = -1;

	public PencilTool(int size, Color color) {
		this.color = color;
		this.size = size;

		saveOldImage();
	}

	public void saveOldImage() {
		// oldImage = MainWindow.panel.copyImage();
	}

	@Override
	public void performAction() {
		// TODO Need a better way of accessing current layer / drawing to it. Maybe
		// implement facade somehow?
		Graphics2D g = Project.getCurrent().getImage().getActiveLayer().getData().createGraphics();

		g.setColor(color);
		g.setStroke(new BasicStroke(size));

		int mx = Input.getMouseX();
		int my = Input.getMouseY();

		if (lastMouseX < 0 || lastMouseY < 0) {
			lastMouseX = mx;
			lastMouseY = my;
		}

		g.drawLine(lastMouseX, lastMouseY, mx, my);

		lastMouseX = mx;
		lastMouseY = my;
	}

	@Override
	public void undoAction() {
		// the new currentImage
		// BufferedImage k = MainWindow.panel.copyImage();
		// MainWindow.panel.setImage(oldImage);
		// oldImage = k;
	}

}
