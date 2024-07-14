package it.crystalnest.leathered_boots.api;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Leathered Boots API.
 */
public final class LeatheredBootsManager {
  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static final ConcurrentHashMap<ResourceLocation, CobwebEntry<LeatheredBootsItem>> LEATHERED_BOOTS = new ConcurrentHashMap<>();

  private LeatheredBootsManager() {}

  /**
   * Provides a {@link BootsRegister} to register {@link LeatheredBootsItem}s.
   *
   * @param modId mod ID.
   * @return {@link BootsRegister}.
   */
  public static BootsRegister register(@NotNull String modId) {
    return new BootsRegister(modId);
  }

  /**
   * Returns the list of all registered {@link LeatheredBootsItem}s.
   *
   * @return the list of all registered {@link LeatheredBootsItem}s.
   */
  public static List<LeatheredBootsItem> getBoots() {
    return LEATHERED_BOOTS.values().stream().map(Supplier::get).toList();
  }

  /**
   * Returns the list of all registered {@link LeatheredBootsItem}s by the specified mod.
   *
   * @param modId mod ID.
   * @return the list of all registered {@link LeatheredBootsItem}s by the specified mod.
   */
  public static List<LeatheredBootsItem> getBoots(@NotNull String modId) {
    return LEATHERED_BOOTS.entrySet().stream().filter(entry -> entry.getKey().getNamespace().equalsIgnoreCase(modId)).map(entry -> entry.getValue().get()).toList();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ResourceLocation}.
   *
   * @param key leathered boots ID.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getBoots(@NotNull ResourceLocation key) {
    return LEATHERED_BOOTS.containsKey(key) ? LEATHERED_BOOTS.get(key).get() : null;
  }

  /**
   * Returns the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s.
   *
   * @return the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s.
   */
  public static List<ItemStack> getBootsStack() {
    return LEATHERED_BOOTS.values().stream().map(item -> item.get().getDefaultInstance()).toList();
  }

  /**
   * Returns the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s by the specified mod.
   *
   * @param modId mod ID.
   * @return the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s by the specified mod.
   */
  public static List<ItemStack> getBootsStack(@NotNull String modId) {
    return LEATHERED_BOOTS.entrySet().stream().filter(entry -> entry.getKey().getNamespace().equalsIgnoreCase(modId)).map(entry -> entry.getValue().get().getDefaultInstance()).toList();
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   *
   * @param key leathered boots ID.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static ItemStack getBootsStack(@NotNull ResourceLocation key) {
    LeatheredBootsItem item = getBoots(key);
    return item == null ? null : item.getDefaultInstance();
  }

  /**
   * Returns the list of all registered mod IDs.
   *
   * @return the list of all registered mod IDs.
   */
  public static List<String> getModIds() {
    return LEATHERED_BOOTS.keySet().stream().map(ResourceLocation::getNamespace).toList();
  }

  /**
   * Register for {@link LeatheredBootsItem}s.
   */
  public record BootsRegister(String modId) {
    /**
     * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
     *
     * @param name base armor material name.
     * @param durabilityFactor durability factor.
     * @param isFireResistant whether the boots are fire-resistant.
     * @param armorMaterial armor material holder.
     * @return {@link CobwebEntry} of the registered {@link LeatheredBootsItem}.
     */
    public synchronized CobwebEntry<LeatheredBootsItem> register(@NotNull String name, int durabilityFactor, boolean isFireResistant, Holder<ArmorMaterial> armorMaterial) {
      ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modId, "leathered_" + name + "_boots");
      if (LEATHERED_BOOTS.containsKey(id)) {
        Constants.LOGGER.error("LeatheredBootsItem [{}] was already registered.", id);
      }
      return LEATHERED_BOOTS.computeIfAbsent(id, key -> CobwebRegistry.ofItems(modId).register(
        key.getPath(),
        Services.ITEM.supplyItem(durabilityFactor, isFireResistant, CobwebRegistry.of(Registries.ARMOR_MATERIAL, Constants.MOD_ID).register("leathered_" + name, supplyLeatheredArmorMaterial(armorMaterial)))
      ));
    }

    /**
     * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
     *
     * @param name armor material name.
     * @param durabilityFactor durability factor.
     * @param armorMaterial armor material holder.
     * @return {@link CobwebEntry} of the registered {@link LeatheredBootsItem}.
     */
    public synchronized CobwebEntry<LeatheredBootsItem> register(@NotNull String name, int durabilityFactor, Holder<ArmorMaterial> armorMaterial) {
      return register(name, durabilityFactor, false, armorMaterial);
    }

    /**
     * Provides a supplier for a new armor material, copying the given one.
     *
     * @param holder armor material to copy.
     * @return armor material supplier.
     */
    private static Supplier<ArmorMaterial> supplyLeatheredArmorMaterial(Holder<ArmorMaterial> holder) {
      return () -> {
        ArmorMaterial armorMaterial = holder.value();
        ResourceLocation armorName = armorMaterial.layers().getFirst().assetName.withPath(path -> "leathered_" + path);
        return new ArmorMaterial(
          armorMaterial.defense(),
          armorMaterial.enchantmentValue(),
          armorMaterial.equipSound(),
          armorMaterial.repairIngredient(),
          ((ArmorItem) Items.LEATHER_BOOTS).getMaterial().value().layers().stream().map(layer -> new ArmorMaterial.Layer(armorName, layer.suffix, layer.dyeable())).toList(),
          armorMaterial.toughness(),
          armorMaterial.knockbackResistance()
        );
      };
    }
  }
}
