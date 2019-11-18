package pixelsmart.events;

@FunctionalInterface
public interface EventListener<T> {
    public void update(T e);
}