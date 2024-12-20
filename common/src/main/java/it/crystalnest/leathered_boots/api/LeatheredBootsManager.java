package it.crystalnest.leathered_boots.api;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
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
   *
   * @param modId mod ID.
   */
  public record BootsRegister(String modId) {
    /**
     * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
     *
     * @param name base armor material name.
     * @param armorMaterial armor material.
     * @param isFireResistant whether the boots are fire-resistant.
     * @return {@link CobwebEntry} of the registered {@link LeatheredBootsItem}.
     */
    public synchronized CobwebEntry<LeatheredBootsItem> register(@NotNull String name, ArmorMaterial armorMaterial, boolean isFireResistant) {
      ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modId, "leathered_" + name + "_boots");
      if (LEATHERED_BOOTS.containsKey(id)) {
        Constants.LOGGER.error("LeatheredBootsItem [{}] was already registered.", id);
      }
      return LEATHERED_BOOTS.computeIfAbsent(id, key -> CobwebRegistry.ofItems(modId).registerItem(key.getPath(), properties -> {
        LeatheredBootsItem item = new LeatheredBootsItem(getLeatheredArmorMaterial(modId, armorMaterial), isFireResistant ? properties.fireResistant() : properties);
        CauldronInteraction.WATER.map().putIfAbsent(item, CauldronInteraction::dyedItemIteration);
        return item;
      }));
    }

    /**
     * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
     *
     * @param name armor material name.
     * @param armorMaterial armor material holder.
     * @return {@link CobwebEntry} of the registered {@link LeatheredBootsItem}.
     */
    public synchronized CobwebEntry<LeatheredBootsItem> register(@NotNull String name, ArmorMaterial armorMaterial) {
      return register(name, armorMaterial, false);
    }

    /**
     * Provides a new armor material copying the given one.
     *
     * @param modId Mod owning the armor material.
     * @param armorMaterial armor material to copy.
     * @return leathered armor material.
     */
    private static ArmorMaterial getLeatheredArmorMaterial(String modId, ArmorMaterial armorMaterial) {
      return new ArmorMaterial(
        armorMaterial.durability(),
        armorMaterial.defense(),
        armorMaterial.enchantmentValue(),
        armorMaterial.equipSound(),
        armorMaterial.toughness(),
        armorMaterial.knockbackResistance(),
        armorMaterial.repairIngredient(),
        ResourceLocation.fromNamespaceAndPath(modId, "leathered_" + armorMaterial.modelId().getPath())
      );
    }
  }
}
