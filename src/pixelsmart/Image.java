package pixelsmart;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.swing.JOptionPane;

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

    public static Image getCurrent() {
        return Project.getCurrent() != null ? Project.getCurrent().getImage() : null;
    }

    public Layer getActiveLayer() {
        return getLayerByIndex(activeLayer);
    }

    public void setActiveLayer(Layer layer) {
        activeLayer = getLayerIndex(layer);
    }

    public void setActiveLayer(String name) {
        setActiveLayer(getLayerByName(name));
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
        if (name == null || name.isEmpty() || containsLayer(x -> x.getName().equalsIgnoreCase(name))) {
            return false;
        }
        boolean success = layers.add(new Layer(this, name));
        LayerList.instance.updateList();
        LayerList.instance.setSelectedIndex(activeLayer);
        return success;
    }

    public boolean addLayer(String name, BufferedImage data) {
        if (name == null || name.isEmpty() || containsLayer(x -> x.getName().equalsIgnoreCase(name))) {
            return false;
        }
        boolean success = layers.add(new Layer(this, name, data));
        LayerList.instance.updateList();
        LayerList.instance.setSelectedIndex(activeLayer);
        return success;
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

    public void setLayerIndex(Layer layer, int index) {
        if (layer == null || !layers.contains(layer) || !isValidLayerIndex(index)) {
            return;
        }

        layers.remove(layer);
        layers.add(index, layer);
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
            g.drawImage(l.getData(), l.getX(), l.getY(), null);
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