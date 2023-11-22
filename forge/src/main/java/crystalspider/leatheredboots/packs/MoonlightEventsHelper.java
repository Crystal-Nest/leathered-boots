package crystalspider.leatheredboots.packs;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

public class MoonlightEventsHelper {
  private static final Map<Class<?>, Queue<Consumer<?>>> LISTENERS = new ConcurrentHashMap<>();

  public static <T> void addListener(Class<T> eventClass, Consumer<T> listener) {
    LISTENERS.computeIfAbsent(eventClass, ev -> new ConcurrentLinkedDeque<>()).add(listener);
  }

  @SuppressWarnings("unchecked")
  public static <T> void postEvent(T event, Class<T> eventClass) {
    var consumers = LISTENERS.get(eventClass);
    if (consumers != null) {
      ((Queue<Consumer<T>>) (Object) consumers).forEach(e -> e.accept(event));
    }
  }
}
