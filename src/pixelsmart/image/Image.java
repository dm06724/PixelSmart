package pixelsmart.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class Image implements Iterable<Layer> {

    private final ArrayList<Layer> layers;
    public static final ImageObservable onImageChanged = new ImageObservable();

    private Layer activeLayer;
    private int width;
    private int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;

        this.layers = new ArrayList<Layer>();
        this.addLayer("Layer 1");
        setActiveLayer(0);
    }

    public Layer getActiveLayer() {
        return activeLayer;
    }

    public void setActiveLayer(Layer layer) {
        activeLayer = layer;
        onImageChanged.notifyListeners(this);
    }

    public void setActiveLayer(int index) {
        setActiveLayer(getLayerByIndex(index));
    }

    public void setActiveLayer(String name) {
        setActiveLayer(getLayerByName(name));
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
        onImageChanged.notifyListeners(this);
    }

    public Layer addLayer(String name) {
        if (name == null || name.isEmpty() || containsLayer(x -> x.getName().equalsIgnoreCase(name))) {
            return null;
        }

        Layer layer = new Layer(this, name);
        layers.add(layer);
        onImageChanged.notifyListeners(this);

        return layer;
    }

    public Layer addLayer(String name, BufferedImage data) {
        Layer layer = addLayer(name);
        if (layer != null) {
            layer.setData(data);
        }
        return layer;
    }

    public boolean removeLayer(Layer layer) {
        int index = getLayerIndex(layer);
        if (index > 0) {
            layers.remove(layer);
            onImageChanged.notifyListeners(this);
            setActiveLayer(0);
            return true;
        }
        return false;
    }

    public boolean removeLayer(String name) {
        return removeLayer(getLayerByName(name));
    }

    public boolean removeLayer(int index) {
        return removeLayer(getLayerByIndex(index));
    }

    public void setLayerIndex(Layer layer, int index) {
        if (layer == null || !layers.contains(layer) || !isValidLayerIndex(index)) {
            return;
        }

        layers.remove(layer);
        layers.add(index, layer);
        onImageChanged.notifyListeners(this);
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

    public BufferedImage getAggregatedData() {
        BufferedImage combined = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();

        for (Layer l : layers) {
            if (l.isVisible()) {
                g.drawImage(l.getData(), l.getX(), l.getY(), null);
            }
        }

        g.dispose();

        return combined;
    }

    public void export() {
        ImageExporter.exportWithDialog(this.getAggregatedData(), "png");
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

    public ArrayList<Layer> getLayers() {
        return new ArrayList<Layer>(layers);
    }

    @Override
    public Iterator<Layer> iterator() {
        return layers.iterator();
    }

    public Layer findLayer(Predicate<Layer> pred) {
        for (Layer layer : layers) {
            if (pred.test(layer)) {
                return layer;
            }
        }
        return null;
    }

    public boolean containsLayer(Predicate<Layer> pred) {
        return findLayer(pred) != null;
    }
}