package it.crystalnest.leathered_boots.api;

import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Leathered Boots API.
 */
public final class LeatheredBootsManager {
  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static final ConcurrentHashMap<ResourceLocation, Supplier<LeatheredBootsItem>> LEATHERED_BOOTS = new ConcurrentHashMap<>();

  private LeatheredBootsManager() {}

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   *
   * @param modId mod ID.
   * @param isFireResistant whether the boots are fire-resistant.
   * @param armorMaterial armor material.
   * @return {@link Supplier} of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Supplier<LeatheredBootsItem> registerBoots(@NotNull String modId, boolean isFireResistant, ArmorMaterial armorMaterial) {
    LeatheredArmorMaterial leatheredArmorMaterial = armorMaterial instanceof LeatheredArmorMaterial leathered ? leathered : new LeatheredArmorMaterial(armorMaterial);
    ResourceLocation id = getKey(modId, leatheredArmorMaterial);
    if (LEATHERED_BOOTS.containsKey(id)) {
      Constants.LOGGER.error("LeatheredBootsItem [{}] was already registered.", id);
    }
    return LEATHERED_BOOTS.computeIfAbsent(id, key -> CobwebRegistry.ofItems(modId).register(key.getPath(), Services.ITEM_HELPER.supplyItem(leatheredArmorMaterial, isFireResistant)));
  }

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   *
   * @param modId mod ID.
   * @param armorMaterial armor material.
   * @return {@link Supplier} of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Supplier<LeatheredBootsItem> registerBoots(@NotNull String modId, ArmorMaterial armorMaterial) {
    return registerBoots(modId, false, armorMaterial);
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   *
   * @param modId mod ID.
   * @param isFireResistant whether the boots are fire-resistant.
   * @param armorMaterials armor materials.
   * @return map of {@link Supplier}s of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Map<ResourceLocation, Supplier<LeatheredBootsItem>> registerBoots(@NotNull String modId, boolean isFireResistant, List<ArmorMaterial> armorMaterials) {
    return armorMaterials.stream().map(armorMaterial -> Map.entry(getKey(modId, armorMaterial), registerBoots(modId, isFireResistant, armorMaterial))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   *
   * @param modId mod ID.
   * @param armorMaterials armor materials.
   * @return map of {@link Supplier}s of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Map<ResourceLocation, Supplier<LeatheredBootsItem>> registerBoots(@NotNull String modId, List<ArmorMaterial> armorMaterials) {
    return registerBoots(modId, false, armorMaterials);
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   *
   * @param modId mod ID.
   * @param isFireResistant whether the boots are fire-resistant.
   * @param armorMaterials armor materials.
   * @return map of {@link Supplier}s of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Map<ResourceLocation, Supplier<LeatheredBootsItem>> registerBoots(@NotNull String modId, boolean isFireResistant, ArmorMaterial... armorMaterials) {
    return registerBoots(modId, isFireResistant, Arrays.asList(armorMaterials));
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   *
   * @param modId mod ID.
   * @param armorMaterials armor materials.
   * @return map of {@link Supplier}s of the registered {@link LeatheredBootsItem}.
   */
  public static synchronized Map<ResourceLocation, Supplier<LeatheredBootsItem>> registerBoots(@NotNull String modId, ArmorMaterial... armorMaterials) {
    return registerBoots(modId, false, armorMaterials);
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
   * @return the list of all registered {@link LeatheredBootsItem}s by the specified mod.
   */
  public static List<LeatheredBootsItem> getBoots(@NotNull String modId) {
    return LEATHERED_BOOTS.entrySet().stream().filter(entry -> entry.getKey().getNamespace().equalsIgnoreCase(modId)).map(entry -> entry.getValue().get()).toList();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ResourceLocation}.
   *
   * @param key leathered boots ID.<br />
   *            See also {@link #getKey(String, ArmorMaterial)}.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getBoots(@NotNull ResourceLocation key) {
    return LEATHERED_BOOTS.getOrDefault(key, () -> null).get();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   *
   * @param modId mod ID.
   * @param armorMaterial armor material.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getBoots(@NotNull String modId, @NotNull ArmorMaterial armorMaterial) {
    return getBoots(getKey(modId, armorMaterial));
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
   * @return the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s by the specified mod.
   */
  public static List<ItemStack> getBootsStack(@NotNull String modId) {
    return LEATHERED_BOOTS.entrySet().stream().filter(entry -> entry.getKey().getNamespace().equalsIgnoreCase(modId)).map(entry -> entry.getValue().get().getDefaultInstance()).toList();
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   *
   * @param key leathered boots ID.<br />
   *            See also {@link #getKey(String, ArmorMaterial)}.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static ItemStack getBootsStack(ResourceLocation key) {
    LeatheredBootsItem item = getBoots(key);
    return item == null ? null : item.getDefaultInstance();
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   *
   * @param modId mod ID.
   * @param armorMaterial armor material.
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static ItemStack getBootsStack(String modId, ArmorMaterial armorMaterial) {
    return getBootsStack(getKey(modId, armorMaterial));
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
   * Returns the {@link ResourceLocation} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.<br />
   * <strong>Note</strong>: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   *
   * @param armorMaterial armor material.
   * @return {@link ResourceLocation} for a {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   */
  public static ResourceLocation getKey(@NotNull String modId, @NotNull ArmorMaterial armorMaterial) {
    return new ResourceLocation(modId, (armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial)).getName() + "_boots");
  }
}
