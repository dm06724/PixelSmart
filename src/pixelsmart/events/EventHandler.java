package pixelsmart.events;

import java.util.ArrayList;

public class EventHandler<T> {
    private ArrayList<EventListener<T>> listeners = new ArrayList<EventListener<T>>();

    public void notifyListeners(T e) {
        for (EventListener<T> listener : listeners) {
            listener.update(e);
        }
    }

    public void addListener(EventListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(EventListener<T> listener) {
        listeners.remove(listener);
    }
}