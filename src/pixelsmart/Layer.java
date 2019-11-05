package pixelsmart;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Layer {
    private Image image;
    private BufferedImage data;
    private String name;
    private int x, y;
    private boolean isVisible = true;

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public void toggleVisible(){
        this.isVisible = !this.isVisible;
    }

    public String toString() {
        return this.name;
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

    public boolean isBaseLayer() {
        return this.image.getBaseLayer() == this;
    }

    public boolean isActiveLayer() {
        return this.image.getActiveLayer() == this;
    }

    public void setAsBaseLayer() {
        this.image.setBaseLayer(this);
    }

    public void setAsActive() {
        this.image.setActiveLayer(this);
    }

    public boolean delete() {
        return this.image.removeLayer(this);
    }

    public int getMouseX() {
        return Input.getMouseX() - this.getX();
    }

    public int getMouseY() {
        return Input.getMouseY() - this.getY();
    }

    public Color getPixelColor(int x, int y) {
        int intColor = data.getRGB(x, y);

        int alphaComponent = (intColor >> 24) & 0xFF;
        int redComponent = (intColor >> 16) & 0xFF;
        int greenComponent = (intColor >> 8) & 0xFF;
        int blueComponent = (intColor >> 0) & 0xFF;
        return new Color(redComponent, greenComponent, blueComponent, alphaComponent);
    }

    public void setPixelColor(int x, int y) {
        // TODO
    }
}