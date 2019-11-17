package pixelsmart.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pixelsmart.Input;
import pixelsmart.MainWindow;
import pixelsmart.MathUtil;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class ImagePanel extends JPanel {
	public static final int RELATIVE_TO_PANEL = 0;
	public static final int RELATIVE_TO_IMAGE = 1;
	public static final int RELATIVE_TO_LAYER = 2;
	private static final double ZOOM_MULTIPLIER = 0.2f;
	private static final double MIN_ZOOM = 0.2;
	private static final double MAX_ZOOM = 5.0;
	private static final long serialVersionUID = -5952682079799751735L;
	private static final Color BACKGROUND_COLOR = new Color(51, 51, 51);
	private static BufferedImage transBackground;
	private Image image;
	private Layer activeLayer;
	private double zoomLevel = 1;

	public ImagePanel() {
		super(new BorderLayout(0, 0));

		try {
			transBackground = ImageIO.read(new File("res/images/TransparentBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ImagePanel get() {
		return MainWindow.getInstance().getPanel();
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		g.setBackground(BACKGROUND_COLOR);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (image == null) {
			return;
		}

		int width = getImageViewWidth();
		int height = getImageViewHeight();

		int x = getImageViewOffsetX();
		int y = getImageViewOffsetY();

		TexturePaint backPaint = new TexturePaint(transBackground, new Rectangle(x, y, 50, 50));

		g.setPaint(backPaint);

		g.fill(new Rectangle2D.Double(x, y, width, height));

		g.setPaint(null);

		// // Draw all layers
		// for (Layer layer : image) {
		// 	if (!layer.isVisible())
		// 		continue;
		// 	Rectangle layerRect = getLayerRect(layer);
		// 	g.drawImage(layer.getData(), layerRect.x, layerRect.y, layerRect.width, layerRect.height, null);
		// }

		g.drawImage(image.getAggregatedData(), x, y, width, height, null);

		// Draw a box around active layer
		if (activeLayer != null) {
			Rectangle layerRect = getLayerRect(activeLayer);
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(layerRect.x, layerRect.y, layerRect.width, layerRect.height);
		}
	}

	private int getImageWidth() {
		return image.getWidth();
	}

	private int getImageHeight() {
		return image.getHeight();
	}

	private int getImageViewWidth() {
		return (int) (getImageWidth() * getZoom());
	}

	private int getImageViewHeight() {
		return (int) (getImageHeight() * getZoom());
	}

	private int getImageViewOffsetX() {
		return (this.getWidth() - getImageViewWidth()) / 2;
	}

	private int getImageViewOffsetY() {
		return (this.getHeight() - getImageViewHeight()) / 2;
	}

	public int getMouseX() {
		return Input.getMouseX();
	}

	public int getMouseY() {
		return Input.getMouseY();
	}

	public int getMouseX(int relativeTo) {
		switch (relativeTo) {
		default:
		case RELATIVE_TO_PANEL:
			return getMouseX();
		case RELATIVE_TO_IMAGE:
			Rectangle imageRect = getImageRect();
			return MathUtil.map(getMouseX() - imageRect.x, 0, imageRect.width, 0, getImageWidth());
		case RELATIVE_TO_LAYER:
			Rectangle layerRect = getLayerRect(activeLayer);
			return MathUtil.map(getMouseX() - layerRect.x, 0, layerRect.width, 0, activeLayer.getWidth());
		}
	}

	public int getMouseY(int relativeTo) {
		switch (relativeTo) {
		default:
		case RELATIVE_TO_PANEL:
			return getMouseY();
		case RELATIVE_TO_IMAGE:
			Rectangle imageRect = getImageRect();
			return MathUtil.map(getMouseY() - imageRect.y, 0, imageRect.height, 0, getImageHeight());
		case RELATIVE_TO_LAYER:
			Rectangle layerRect = getLayerRect(activeLayer);
			return MathUtil.map(getMouseY() - layerRect.y, 0, layerRect.height, 0, activeLayer.getHeight());
		}
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.activeLayer = image.getBaseLayer();
	}

	public Layer getActiveLayer() {
		return this.activeLayer;
	}

	public void setActiveLayer(Layer layer) {
		this.activeLayer = layer;
	}

	public void setActiveLayer(int index) {
		setActiveLayer(image.getLayerByIndex(index));
	}

	public void setActiveLayer(String name) {
		setActiveLayer(image.getLayerByName(name));
	}

	public void setZoom(double level) {
		zoomLevel = MathUtil.clamp(level, MIN_ZOOM, MAX_ZOOM);
	}

	public void zoomIn() {
		setZoom(zoomLevel + zoomLevel * ZOOM_MULTIPLIER);
	}

	public void zoomOut() {
		setZoom(zoomLevel - zoomLevel * ZOOM_MULTIPLIER);
	}

	public void zoomIn(double multiplier) {
		setZoom(zoomLevel + zoomLevel * multiplier * ZOOM_MULTIPLIER);
	}

	public void zoomOut(double multiplier) {
		setZoom(zoomLevel - zoomLevel * multiplier * ZOOM_MULTIPLIER);
	}

	public double getZoom() {
		return this.zoomLevel;
	}

	public Rectangle getImageRect() {
		return new Rectangle(getImageViewOffsetX(), getImageViewOffsetY(), getImageViewWidth(), getImageViewWidth());
	}

	public Rectangle getLayerRect(Layer layer) {
		if (layer == null) {
			return null;
		}
		Rectangle imageRect = getImageRect();
		int layerX = (int) (layer.getX() * getZoom());
		int layerY = (int) (layer.getY() * getZoom());
		int layerWidth = (int) (layer.getWidth() * getZoom());
		int layerHeight = (int) (layer.getHeight() * getZoom());
		return new Rectangle(imageRect.x + layerX, imageRect.y + layerY, layerWidth, layerHeight);
	}
}
