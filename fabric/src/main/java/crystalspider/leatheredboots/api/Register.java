package crystalspider.leatheredboots.api;

import java.util.function.BiFunction;

import net.minecraft.util.registry.Registry;

/**
 * Register provided by {@link RegisterProvider} for a specified Minecraft {@link Registry}.
 */
@FunctionalInterface
public interface Register<T> extends BiFunction<String, T, T> {}
