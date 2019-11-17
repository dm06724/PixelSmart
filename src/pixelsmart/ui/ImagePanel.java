package pixelsmart.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pixelsmart.Input;
import pixelsmart.Project;
import pixelsmart.image.Image;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -5952682079799751735L;
	private static final Color BACKGROUND_COLOR = new Color(51, 51, 51);
	private static BufferedImage transBackground;
	private Image image;

	public ImagePanel() {
		super(new BorderLayout(5, 5));

		try {
			transBackground = ImageIO.read(new File("res/images/TransparentBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		g.setBackground(BACKGROUND_COLOR);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (image == null) {
			return;
		}

		int width = getImageWidth();
		int height = getImageHeight();

		int x = mouseOffsetX();
		int y = mouseOffsetY();

		TexturePaint backPaint = new TexturePaint(transBackground, new Rectangle2D.Double(x, y, 50, 50));

		g.setPaint(backPaint);

		g.fill(new Rectangle2D.Double(x, y, width, height));

		g.setPaint(null);

		g.drawImage(image.getAggregatedData(), x, y, width, height, null);
	}

	private int getImageWidth() {
		return (int) (image.getWidth() * Project.getCurrent().getZoomLevel());
	}

	private int getImageHeight() {
		return (int) (image.getHeight() * Project.getCurrent().getZoomLevel());
	}

	private int mouseOffsetX() {
		return (this.getWidth() - getImageWidth()) / 2;
	}

	private int mouseOffsetY() {
		return (this.getHeight() - getImageHeight()) / 2;
	}

	public int getMouseX() {
		return Input.getMouseX() - mouseOffsetX();
	}

	public int getMouseY() {
		return Input.getMouseY() - mouseOffsetY();
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
