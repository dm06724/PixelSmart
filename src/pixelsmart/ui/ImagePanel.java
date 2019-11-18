package pixelsmart.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pixelsmart.MathUtil;
import pixelsmart.events.EventHandler;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class ImagePanel extends JPanel {
    public static final int RELATIVE_TO_PANEL = 0;
    public static final int RELATIVE_TO_IMAGE = 1;
    public static final int RELATIVE_TO_LAYER = 2;

    public static final EventHandler<Layer> onActiveLayerChanged = new EventHandler<Layer>();
    public static final EventHandler<Integer> onZoomChanged = new EventHandler<Integer>();

    private static final double ZOOM_MULTIPLIER = 0.2f;
    private static final double MIN_ZOOM = 0.2;
    private static final double MAX_ZOOM = 50.0;
    private static final long serialVersionUID = -5952682079799751735L;
    private static final Color BACKGROUND_COLOR = new Color(80, 80, 80);

    private BufferedImage transBackground;
    private Image image;
    private Layer activeLayer;
    private double zoomLevel = 1;
    private Path2D.Double clip;

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

        Rectangle rect = getImageViewRect();

        TexturePaint backPaint = new TexturePaint(transBackground, new Rectangle(rect.x, rect.y, 20, 20));

        g.setPaint(backPaint);

        g.fill(rect);

        g.setPaint(null);

        // Draw the layers
        g.drawImage(image.getAggregatedData(), rect.x, rect.y, rect.width, rect.height, null);

        // Draw a box around active layer
        if (activeLayer != null) {
            Rectangle layerRect = getLayerViewRect(activeLayer);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(layerRect.x, layerRect.y, layerRect.width, layerRect.height);
        }

        // TODO Draw Selection Clip Box
        if (this.clip != null) {
            g.setColor(Color.YELLOW);
            g.draw(getClip(RELATIVE_TO_IMAGE));
        }
    }

    private int getImageViewWidth() {
        return (int) (image.getWidth() * getZoom());
    }

    private int getImageViewHeight() {
        return (int) (image.getHeight() * getZoom());
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

    public int transformX(int x, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return x;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return MathUtil.map(x - imageRect.x, 0, imageRect.width, 0, image.getWidth());
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return MathUtil.map(x - layerRect.x, 0, layerRect.width, 0, activeLayer.getWidth());
        }
    }

    public int transformY(int y, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return y;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return MathUtil.map(y - imageRect.y, 0, imageRect.height, 0, image.getHeight());
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return MathUtil.map(y - layerRect.y, 0, layerRect.height, 0, activeLayer.getHeight());
        }
    }

    public int inverseTransformX(int x, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return x;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return imageRect.x + MathUtil.map(x, 0, image.getWidth(), 0, imageRect.width);
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return layerRect.x + MathUtil.map(x - layerRect.x, 0, activeLayer.getWidth(), 0, layerRect.width);
        }
    }

    public int inverseTransformY(int y, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return y;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return imageRect.y + MathUtil.map(y, 0, image.getHeight(), 0, imageRect.height);
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return layerRect.y + MathUtil.map(y, 0, activeLayer.getHeight(), 0, layerRect.height);
        }
    }

    public int getMouseX(int relativeTo) {
        return transformX(getMouseX(), relativeTo);
    }

    public int getMouseY(int relativeTo) {
        return transformY(getMouseY(), relativeTo);
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;

        setActiveLayer(image != null ? image.getBaseLayer() : null);
    }

    public Layer getActiveLayer() {
        return this.activeLayer;
    }

    public void setActiveLayer(Layer layer) {
        Layer old = this.activeLayer;
        this.activeLayer = layer;

        if (layer != old) {
            onActiveLayerChanged.notifyListeners(layer);
        }
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

    public Shape getClip(int relativeTo) {
        if (relativeTo == RELATIVE_TO_PANEL) {
            Rectangle shapeRect = clip.getBounds();
            Rectangle panelRect = getRect();

            AffineTransform transform = transformRect(shapeRect, panelRect);
            return transform.createTransformedShape(clip);
        } else if (relativeTo == RELATIVE_TO_IMAGE) {
            return this.clip;
        } else if (relativeTo == RELATIVE_TO_LAYER) {
            Rectangle shapeRect = clip.getBounds();
            Rectangle layerRect = getLayerViewRect(activeLayer);

            AffineTransform transform = transformRect(shapeRect, layerRect);
            return transform.createTransformedShape(clip);
        } else {
            return this.clip;
        }
    }

    public void setClip(Shape clip) {
        this.clip = new Path2D.Double(clip);
    }

    public Rectangle getRect() {
        return new Rectangle(0, 0, this.getWidth(), this.getHeight());
    }

    public Rectangle getImageViewRect() {
        return new Rectangle(getImageViewOffsetX(), getImageViewOffsetY(), getImageViewWidth(), getImageViewWidth());
    }

    public Rectangle getLayerViewRect(Layer layer) {
        if (layer == null) {
            return null;
        }
        Rectangle imageViewRect = getImageViewRect();
        int layerX = (int) (layer.getX() * getZoom());
        int layerY = (int) (layer.getY() * getZoom());
        int layerWidth = (int) (layer.getWidth() * getZoom());
        int layerHeight = (int) (layer.getHeight() * getZoom());
        return new Rectangle(imageViewRect.x + layerX, imageViewRect.y + layerY, layerWidth, layerHeight);
    }

    private AffineTransform transformRect(Rectangle from, Rectangle to) {
        AffineTransform t = new AffineTransform();
        t.translate(to.getMinX(), to.getMinY());
        t.scale(to.getWidth() / from.getWidth(), to.getHeight() / from.getHeight());
        t.translate(-from.getMinX(), -from.getMinY());
        return t;
    }
}
