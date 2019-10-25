package pixelsmart;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Image {
    private final ArrayList<Layer> layers = new ArrayList<Layer>();

    private int activeLayer;
    private int width;
    private int height;

    protected Image(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Layer getActiveLayer() {
        return layers.get(activeLayer);
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

    public boolean addLayer(Layer layer) {
        return layer != null ? layers.add(layer) : false;
    }

    public boolean removeLayer(String name) {
        return layers.removeIf(x -> x.getName().equals(name));
    }

    public boolean removeLayer(Layer layer) {
        return layers.remove(layer);
    }

    public boolean removeLayer(int index) {
        return isValidLayerIndex(index) && layers.remove(index) != null;
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

    public BufferedImage getAggrigateImage() {
        BufferedImage combined = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();

        for (Layer l : layers){
            g.drawImage(l.getImage(), l.getX(), l.getY(), null);
        }

        g.dispose();

        return combined;
    }

    public void exportAs(String format, String path) {
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageExporter.export(getAggrigateImage(), format, file);
        }
    }

    private boolean isValidLayerIndex(int index) {
        return index < 0 || index >= layers.size();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}