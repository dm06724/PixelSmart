package pixelsmart.image;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Layer {
    private Image image;
    private BufferedImage data;
    private String name;
    private boolean isVisible = true;
    private int x, y, width, height;

    protected Layer(Image image, String name) {
        this.name = name;
        this.image = image;
        this.clear();
    }

    protected Layer(Image image, String name, BufferedImage data) {
        this.name = name;
        this.image = image;

        if (data == null) {
            this.clear();
        } else {
            this.setData(data);
        }
    }

    protected Layer(Image image, String name, BufferedImage data, int x, int y) {
        this(image, name, data);
        this.setPosition(x, y);
    }

    public Image getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public void setData(BufferedImage data) {
        this.data = data;
    }

    public BufferedImage getData() {
        return data;
    }

    public BufferedImage copyData() {
        ColorModel cm = data.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = data.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void clear() {
        this.data = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width <= 0 ? this.data.getWidth() : this.width;
    }

    public int getHeight() {
        return this.height <= 0 ? this.data.getHeight() : this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRect() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public void toggleVisible() {
        this.isVisible = !this.isVisible;
    }

    public String toString() {
        return this.name + (isBaseLayer() ? " (Base)" : "") + (isVisible ? "" : " (H)");
    }

    public void setIndex(int index) {
        this.image.setLayerIndex(this, index);
    }

    public int getIndex() {
        return this.image.getLayerIndex(this);
    }

    public void moveUp() {
        this.setIndex(this.getIndex() + 1);
    }

    public void moveDown() {
        this.setIndex(this.getIndex() - 1);
    }

    public Layer getNextLayer() {
        return this.image.getLayerByIndex(getIndex() + 1);
    }

    public Layer getPreviousLayer() {
        return this.image.getLayerByIndex(getIndex() - 1);
    }

    public Layer getNextLayer(boolean wrap) {
        int index = getIndex() + 1;
        if (wrap) {
            index %= image.layerCount();
        }
        return this.image.getLayerByIndex(index);
    }

    public Layer getPreviousLayer(boolean wrap) {
        int index = getIndex() - 1;
        if (wrap) {
            index = (index + image.layerCount()) % image.layerCount();
        }
        return this.image.getLayerByIndex(index);
    }

    public boolean isBaseLayer() {
        return this.image.getBaseLayer() == this;
    }

    public void setAsBaseLayer() {
        this.image.setBaseLayer(this);
    }

    public boolean delete() {
        return this.image.removeLayer(this);
    }

    public Color getPixelColor(int x, int y) {
        int intColor = data.getRGB(x, y);

        int alphaComponent = (intColor >> 24) & 0xFF;
        int redComponent = (intColor >> 16) & 0xFF;
        int greenComponent = (intColor >> 8) & 0xFF;
        int blueComponent = (intColor >> 0) & 0xFF;
        return new Color(redComponent, greenComponent, blueComponent, alphaComponent);
    }

    public void setPixelColor(int x, int y, Color color) {
        this.data.setRGB(x, y, color.getRGB());
    }
}