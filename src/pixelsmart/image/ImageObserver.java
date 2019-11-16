package pixelsmart.image;

@FunctionalInterface
public interface ImageObserver {
    public static final int NEW_LAYER = 0;
    public static final int REMOVE_LAYER = 1;
    public static final int REARRANGE_LAYER = 2;

    public void onImageUpdated(Image image);
}