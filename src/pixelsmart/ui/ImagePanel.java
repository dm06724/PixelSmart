package pixelsmart.ui;

import java.awt.BasicStroke;
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

import pixelsmart.events.EventHandler;
import pixelsmart.events.EventListener;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;
import pixelsmart.tools.DrawingTool;
import pixelsmart.tools.ToolManager;
import pixelsmart.util.MathUtil;

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = -5952682079799751735L;

    public static final int RELATIVE_TO_PANEL = 0;
    public static final int RELATIVE_TO_IMAGE = 1;
    public static final int RELATIVE_TO_LAYER = 2;

    private final EventHandler<Layer> onActiveLayerChanged = new EventHandler<Layer>();
    private final EventHandler<Double> onZoomChanged = new EventHandler<Double>();
    private final EventHandler<Image> onImageChanged = new EventHandler<Image>();
    private final EventListener<Layer> addLayerListener;
    private final EventListener<Layer> deleteLayerListener;

    private static final double ZOOM_MULTIPLIER = 0.2f;
    private static final double MIN_ZOOM = 0.2;
    private static final double MAX_ZOOM = 50.0;
    private static final Color BACKGROUND_COLOR = new Color(80, 80, 80);

    private BufferedImage transBackground;
    private Image image;
    private Layer activeLayer;
    private int imageOffsetX;
    private int imageOffsetY;
    private double zoomLevel = 1;
    private Path2D.Double clip;

    public ImagePanel() {
        super(new BorderLayout(0, 0));

        try {
            transBackground = ImageIO.read(new File("res/images/TransparentBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addLayerListener = layer -> setActiveLayer(layer);
        deleteLayerListener = layer -> setActiveLayer(0);

        this.addMouseMotionListener(Input.getInstance());
        this.addMouseListener(Input.getInstance());
        this.addMouseWheelListener(Input.getInstance());
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

        DrawingTool drawThis = null;
        if(ToolManager.getInstance().getTool() instanceof DrawingTool)
        {
        	drawThis = (DrawingTool)ToolManager.getInstance().getTool();
        }
        
        BufferedImage aggregatedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D combinedGraphics = aggregatedImage.createGraphics();
        
        // Draw the layers
        for(Layer l : image)
    	{
        	if(l.isVisible())
        	{
	        	if(drawThis != null)
	        	{
	        		if(drawThis.layerAppliedTo == l)
	        		{
	        			//create temp buff image, draw into that
	        			BufferedImage tempBuffImage = l.copyData();
	        			drawThis.drawTemporaryImage(tempBuffImage.createGraphics());
	        			combinedGraphics.drawImage(tempBuffImage, l.getX(), l.getY(), l.getWidth(), l.getHeight(), null);
	        		}
	        		else
	        		{
	        			combinedGraphics.drawImage(l.getData(), l.getX(), l.getY(), l.getWidth(), l.getHeight(), null);
	        		}
	        	}
	        	else
	        	{
	        		combinedGraphics.drawImage(l.getData(), l.getX(), l.getY(), l.getWidth(), l.getHeight(), null);
	        	}
        	}
        	
    	}
        
        g.drawImage(aggregatedImage, rect.x, rect.y, rect.width, rect.height, null);
        
        //if(drawThis!=null)
        //{
        //	drawThis.drawTemporaryImage(g);
        //}
        
        g.setStroke(new BasicStroke());
        
        // Draw a box around active layer
        if (activeLayer != null) {
            Rectangle layerRect = getLayerViewRect(activeLayer);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(layerRect.x, layerRect.y, layerRect.width, layerRect.height);
        }

        if (this.clip != null) {

            g.setColor(Color.YELLOW);
            g.draw(getClip(RELATIVE_TO_PANEL));
        }
    }

    private int getImageViewWidth() {
        return (int) (image.getWidth() * getZoom());
    }

    private int getImageViewHeight() {
        return (int) (image.getHeight() * getZoom());
    }

    private int getImageViewOffsetX() {
        return imageOffsetX + (this.getWidth() - getImageViewWidth()) / 2;
    }

    private int getImageViewOffsetY() {
        return imageOffsetY + (this.getHeight() - getImageViewHeight()) / 2;
    }

    public void setImageOffsetX(int offset) {
        this.imageOffsetX = offset;
    }

    public void setImageOffsetY(int offset) {
        this.imageOffsetY = offset;
    }

    public void setImageOffset(int x, int y) {
        this.imageOffsetX = x;
        this.imageOffsetY = y;
    }

    /**
     * Transforms a x position from panel space to another space.
     */
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

    /**
     * Transforms a y position from panel space to another space.
     */
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

    /**
     * Transforms an x position from one space to panel space.
     */
    public int inverseTransformX(int x, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return x;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return MathUtil.map(x, 0, image.getWidth(), imageRect.x, imageRect.x + imageRect.width);
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return MathUtil.map(x, 0, activeLayer.getWidth(), layerRect.x, layerRect.x + layerRect.width);
        }
    }

    /**
     * Transforms a y position from one space to panel space.
     */
    public int inverseTransformY(int y, int relativeTo) {
        switch (relativeTo) {
        default:
        case RELATIVE_TO_PANEL:
            return y;
        case RELATIVE_TO_IMAGE:
            Rectangle imageRect = getImageViewRect();
            return MathUtil.map(y, 0, image.getHeight(), imageRect.y, imageRect.y + imageRect.height);
        case RELATIVE_TO_LAYER:
            Rectangle layerRect = getLayerViewRect(activeLayer);
            return MathUtil.map(y, 0, activeLayer.getHeight(), layerRect.y, layerRect.y + layerRect.height);
        }
    }

    public int getMouseX(int relativeTo) {
        return transformX(Input.getMouseX(), relativeTo);
    }

    public int getMouseY(int relativeTo) {
        return transformY(Input.getMouseY(), relativeTo);
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        Image old = this.image;

        if (image == old) {
            return;
        }

        this.image = image;
        onImageChanged.notifyListeners(image);

        if (old != null) {
            old.removeLayerAddedListener(addLayerListener);
            old.removeLayerDeletedListener(deleteLayerListener);
        }
        if (image != null) {
            image.addLayerAddedListener(addLayerListener);
            image.addLayerDeletedListener(deleteLayerListener);
        }

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
        double old = zoomLevel;
        zoomLevel = MathUtil.clamp(level, MIN_ZOOM, MAX_ZOOM);

        if (zoomLevel != old) {
            onZoomChanged.notifyListeners(zoomLevel);
        }
    }

    public void zoomIn() {
        zoomIn(1);
    }

    public void zoomOut() {
        zoomOut(1);
    }

    public void zoomIn(double multiplier) {
        setZoom(zoomLevel + zoomLevel * multiplier * ZOOM_MULTIPLIER);
    }

    public void zoomOut(double multiplier) {
        setZoom(zoomLevel - zoomLevel * multiplier * ZOOM_MULTIPLIER);
    }

    /**
     * Zooms in around a point in image space.
     * 
     * @param multiplier Scales how much to zoom by
     * @param aroundX    The x-coord to zoom around
     * @param aroundY    The y-coord to zoom around
     */
    public void zoomInAround(double multiplier, int aroundX, int aroundY) {
        int startX = inverseTransformX(aroundX, RELATIVE_TO_IMAGE);
        int startY = inverseTransformY(aroundY, RELATIVE_TO_IMAGE);

        zoomIn(multiplier);

        int finishX = inverseTransformX(aroundX, RELATIVE_TO_IMAGE);
        int finishY = inverseTransformY(aroundY, RELATIVE_TO_IMAGE);

        imageOffsetX -= finishX - startX;
        imageOffsetY -= finishY - startY;
    }

    /**
     * Zooms out around a point in image space.
     * 
     * @param multiplier Scales how much to zoom
     * @param aroundX    The x-coord to zoom around
     * @param aroundY    The y-coord to zoom around
     */
    public void zoomOutAround(double multiplier, int aroundX, int aroundY) {
        int startX = inverseTransformX(aroundX, RELATIVE_TO_IMAGE);
        int startY = inverseTransformY(aroundY, RELATIVE_TO_IMAGE);

        zoomOut(multiplier);

        int finishX = inverseTransformX(aroundX, RELATIVE_TO_IMAGE);
        int finishY = inverseTransformY(aroundY, RELATIVE_TO_IMAGE);

        imageOffsetX -= finishX - startX;
        imageOffsetY -= finishY - startY;
    }

    public void zoomInAround(int aroundX, int aroundY) {
        zoomInAround(1, aroundX, aroundY);
    }

    public void zoomOutAround(int aroundX, int aroundY) {
        zoomOutAround(1, aroundX, aroundY);
    }

    public double getZoom() {
        return this.zoomLevel;
    }

    public Shape getClip(int relativeTo) {
        if (relativeTo == RELATIVE_TO_PANEL) {
            AffineTransform transform = AffineTransform.getTranslateInstance(getImageViewOffsetX(),
                    getImageViewOffsetY());
            transform.concatenate(AffineTransform.getScaleInstance(getZoom(), getZoom()));
            return transform.createTransformedShape(getClip(RELATIVE_TO_IMAGE));
        } else if (relativeTo == RELATIVE_TO_LAYER) {
            if (activeLayer == null || clip == null) {
                return null;
            }
            AffineTransform transform = AffineTransform.getTranslateInstance(-activeLayer.getX(), -activeLayer.getY());
            return transform.createTransformedShape(clip);
        } else {
            return this.clip;
        }
    }

    public void setClip(Shape clip) {
        this.clip = clip == null ? null : new Path2D.Double(clip);
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

    public void addZoomListener(EventListener<Double> listener) {
        onZoomChanged.addListener(listener);
    }

    public void removeZoomListener(EventListener<Double> listener) {
        onZoomChanged.removeListener(listener);
    }

    public void addActiveLayerChangedListener(EventListener<Layer> listener) {
        onActiveLayerChanged.addListener(listener);
    }

    public void removeActiveLayerChangedListener(EventListener<Layer> listener) {
        onActiveLayerChanged.removeListener(listener);
    }

    public void addImageChangedListener(EventListener<Image> listener) {
        onImageChanged.addListener(listener);
    }

    public void removeImageChangedListener(EventListener<Image> listener) {
        onImageChanged.removeListener(listener);
    }
}
