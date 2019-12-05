package pixelsmart.image;

import java.util.Iterator;

public class ImageLayerIterator implements Iterator<Layer> {

    private Image image;
    private int index;

    public ImageLayerIterator(Image image) {
        this.image = image;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return image.getLayerByIndex(index) != null;
    }

    @Override
    public Layer next() {
        return image.getLayerByIndex(index++);
    }
}