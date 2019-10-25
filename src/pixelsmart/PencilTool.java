package pixelsmart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PencilTool implements Tool {

	// should be able to keep track of its size, color, and where it gets applied
	// must maintain the image before it was modified so it can undo.

	private Color c;
	private int size = 1;

	private BufferedImage oldImage = null;

	public PencilTool(int size, Color color) {
		this.c = color;
		this.size = size;

		saveOldImage();
	}

	public void saveOldImage() {
		oldImage = MainWindow.myP.copyImage();
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		Graphics2D g = MainWindow.myP.getImage().createGraphics();

		g.setColor(c);
		g.fillRect(MainWindow.getMouseX(), MainWindow.getMouseY(), size, size);
	}

	@Override
	public void undoAction() {
		// TODO Auto-generated method stub

		// the new currentImage
		BufferedImage k = MainWindow.myP.copyImage();
		MainWindow.myP.setImage(oldImage);
		oldImage = k;
	}

}
