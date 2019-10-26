package pixelsmart;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Image implements Iterable<Layer> {
    private final ArrayList<Layer> layers;

    private int activeLayer;
    private int width;
    private int height;

    protected Image(int width, int height) {
        this.width = width;
        this.height = height;

        this.layers = new ArrayList<Layer>();
        this.addLayer("Base");
        setActiveLayer(0);
    }

    public Layer getActiveLayer() {
        return getLayerByIndex(activeLayer);
    }

    public void setActiveLayer(Layer layer) {
        activeLayer = getLayerIndex(layer);
    }

    public void setActiveLayer(String name) {
        activeLayer = getLayerIndex(getLayerByName(name));
    }

    public void setActiveLayer(int index) {
        activeLayer = isValidLayerIndex(index) ? index : -1;
    }

    public Layer getBaseLayer() {
        return getLayerByIndex(0);
    }

    public void setBaseLayer(Layer layer) {
        if (layer == null)
            return;
        if (layers.contains(layer))
            layers.remove(layer);
        layers.add(0, layer);
    }

    public boolean addLayer(String name) {
        return layers.add(new Layer(this, name));
    }

    public boolean addLayer(String name, BufferedImage data) {
        return layers.add(new Layer(this, name, data));
    }

    public boolean removeLayer(Layer layer) {
        int index = getLayerIndex(layer);
        if (index > 0) {
            layers.remove(layer);
            return true;
        }
        return false;
    }

    public boolean removeLayer(String name) {
        return removeLayer(getLayerByName(name));
    }

    public boolean removeLayer(int index) {
        return index > 0 && removeLayer(getLayerByIndex(index));
    }

    public int getLayerIndex(Layer layer) {
        return layers.indexOf(layer);
    }

    public Layer getLayerByName(String name) {
        for (Layer l : layers) {
            if (l.getName().equals(name))
                return l;
        }

        return null;
    }

    public Layer getLayerByIndex(int index) {
        return isValidLayerIndex(index) ? layers.get(index) : null;
    }

    public BufferedImage getAggregatedImage() {
        BufferedImage combined = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();

        for (Layer l : layers) {
            g.drawImage(l.getData(), l.getX(), l.getY(), null);
        }

        g.dispose();

        return combined;
    }

    public void export() {
        ImageExporter.exportWithDialog(this.getAggregatedImage(), "png");
    }

    private boolean isValidLayerIndex(int index) {
        return index >= 0 && index < layers.size();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int layerCount() {
        return layers.size();
    }

    @Override
    public Iterator<Layer> iterator() {
        return layers.iterator();
    }
}