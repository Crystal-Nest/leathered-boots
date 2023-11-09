package crystalspider.leatheredboots.api;

import java.util.function.BiFunction;

@FunctionalInterface
public interface Register<T> extends BiFunction<String, T, T> {}
