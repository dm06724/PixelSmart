package pixelsmart.image;

@FunctionalInterface
public interface ImageObserver {
    public void onImageUpdated(Image image);
}