package pixelsmart.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import pixelsmart.events.EventHandler;
import pixelsmart.events.EventListener;

public class Image implements Iterable<Layer> {

    private final ArrayList<Layer> layers;
    private final EventHandler<Image> onImageUpdated = new EventHandler<Image>();

    private int width;
    private int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;

        this.layers = new ArrayList<Layer>();
        this.addLayer("Layer 1");
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
        onImageUpdated.notifyListeners(this);
    }

    public Layer addLayer(String name) {
        if (name == null || name.isEmpty() || containsLayer(x -> x.getName().equalsIgnoreCase(name))) {
            return null;
        }

        Layer layer = new Layer(this, name);
        layers.add(layer);
        onImageUpdated.notifyListeners(this);

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
            onImageUpdated.notifyListeners(this);
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
        onImageUpdated.notifyListeners(this);
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
                g.drawImage(l.getData(), l.getX(), l.getY(), l.getWidth(), l.getHeight(), null);
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

    public void addUpdateListener(EventListener<Image> listener){
        onImageUpdated.addListener(listener);
    }

    public void removeUpdateListener(EventListener<Image> listener){
        onImageUpdated.removeListener(listener);
    }
}