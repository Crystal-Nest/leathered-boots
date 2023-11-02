package crystalspider.leatheredboots.api;

@FunctionalInterface
public interface Register<T> extends BiFunction<String, T, T> {}
// public interface Register<T> {
//   <R extends T> R apply(String key, R value);
// }
