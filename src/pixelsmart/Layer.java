package pixelsmart;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Layer {
    private Image image;
    private BufferedImage data;
    private String name;
    private int x, y;

    protected Layer(Image image, String name) {
        this.name = name;
        this.image = image;
        this.data = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    protected Layer(Image image, String name, BufferedImage data) {
        this(image, name);
        this.data = data;
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

    public BufferedImage copyData(){
        ColorModel cm = data.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = data.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
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

    public int getIndex() {
        return this.image.getLayerIndex(this);
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
}