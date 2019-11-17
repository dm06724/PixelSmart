package pixelsmart.image;

import java.util.ArrayList;

public class ImageObservable {
    private ArrayList<ImageObserver> listeners = new ArrayList<ImageObserver>();

    public ImageObservable() {

    }

    public void addListener(ImageObserver listener) {
        listeners.add(listener);
    }

    public void removeListener(ImageObserver listener){
        listeners.remove(listener);
    }

    public void notifyListeners(Image image) {
        for(ImageObserver o : listeners){
            o.onImageUpdated(image);
        }
    }
}