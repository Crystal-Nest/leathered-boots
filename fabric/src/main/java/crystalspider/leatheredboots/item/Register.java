package crystalspider.leatheredboots.item;

import java.util.function.BiFunction;

public interface Register<T> extends BiFunction<String, T, T> {}
