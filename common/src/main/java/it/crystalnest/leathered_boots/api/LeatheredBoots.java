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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Leathered Boots API.
 */
public final class LeatheredBoots {
  private LeatheredBoots() {}

  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static final ConcurrentHashMap<ResourceLocation, Supplier<LeatheredBootsItem>> leatheredBoots = new ConcurrentHashMap<>();

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   * 
   * @param isFireResistant
   * @param armorMaterial
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String modId, boolean isFireResistant, ArmorMaterial armorMaterial) {
    LeatheredArmorMaterial leatheredArmorMaterial = armorMaterial instanceof LeatheredArmorMaterial leathered ? leathered : new LeatheredArmorMaterial(armorMaterial);
    ResourceLocation key = getKeyFor(modId, leatheredArmorMaterial);
    if (!leatheredBoots.containsKey(key)) {
      leatheredBoots.put(key, CobwebRegistry.ofItems(modId).register(key.getPath(), Services.ITEM_HELPER.supplyItem(leatheredArmorMaterial, isFireResistant)));
      return key;
    }
    Constants.LOGGER.error("LeatheredBootsItem [{}] was already registered with the following value: {}", key, leatheredBoots.get(key));
    return null;
  }

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial} setting fire resistance to {@code false}.
   * 
   * @param armorMaterial
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String modId, ArmorMaterial armorMaterial) {
    return registerLeatheredBoots(modId, false, armorMaterial);
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param isFireResistant
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. A {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String modId, boolean isFireResistant, List<ArmorMaterial> armorMaterials) {
    List<ResourceLocation> resourceLocations = new ArrayList<>();
    for (ArmorMaterial armorMaterial : armorMaterials) {
      resourceLocations.add(registerLeatheredBoots(modId, isFireResistant, armorMaterial));
    }
    return resourceLocations;
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. A {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String modId, List<ArmorMaterial> armorMaterials) {
    return registerLeatheredBoots(modId, false, armorMaterials);
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param isFireResistant
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. A {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String modId, boolean isFireResistant, ArmorMaterial... armorMaterials) {
    return registerLeatheredBoots(modId, isFireResistant, Arrays.asList(armorMaterials));
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. A {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String modId, ArmorMaterial... armorMaterials) {
    return registerLeatheredBoots(modId, false, armorMaterials);
  }

  /**
   * Returns the list of all registered {@link LeatheredBootsItem}s.
   * 
   * @return the list of all registered {@link LeatheredBootsItem}s.
   */
  public static List<LeatheredBootsItem> getLeatheredBoots() {
    return leatheredBoots.values().stream().map(Supplier::get).toList();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ResourceLocation}.
   * 
   * @param resourceLocation
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getLeatheredBoots(ResourceLocation resourceLocation) {
    return leatheredBoots.get(resourceLocation).get();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getLeatheredBoots(String modId, ArmorMaterial armorMaterial) {
    return getLeatheredBoots(getKeyFor(modId, armorMaterial));
  }

  /**
   * Returns the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s.
   * 
   * @return the list of all {@link ItemStack}s of all registered {@link LeatheredBootsItem}s.
   */
  public static List<ItemStack> getLeatheredBootsStack() {
    return leatheredBoots.values().stream().map(item -> item.get().getDefaultInstance()).toList();
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ResourceLocation}.
   * 
   * @param resourceLocation
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(ResourceLocation resourceLocation) {
    LeatheredBootsItem item = getLeatheredBoots(resourceLocation);
    if (item != null) {
      return item.getDefaultInstance();
    }
    return null;
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@code modId} and {@code itemId}.
   * 
   * @param itemId
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String itemId) {
    return getLeatheredBootsStack(new ResourceLocation(Constants.MOD_ID, itemId));
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String modId, ArmorMaterial armorMaterial) {
    return getLeatheredBootsStack(getKeyFor(modId, armorMaterial));
  }

  /**
   * Returns the list of all registered mod ids.
   * 
   * @return the list of all registered mod ids.
   */
  public static List<String> getModIds() {
    return leatheredBoots.keySet().stream().map(ResourceLocation::getNamespace).toList();
  }

  public static List<LeatheredBootsItem> getLeatheredBoots(@NotNull String modId) {
    return leatheredBoots.entrySet().stream().filter(entry -> entry.getKey().getNamespace().equalsIgnoreCase(modId)).map(entry -> entry.getValue().get()).toList();
  }

  /**
   * Returns the {@link ResourceLocation} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.
   * <p>
   * Note: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   * 
   * @param armorMaterial
   * @return {@link ResourceLocation} for a {@link LeatheredBootsItem}.
   */
  public static ResourceLocation getKeyFor(String modId, ArmorMaterial armorMaterial) {
    return new ResourceLocation(modId, (armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial)).getName() + "_boots");
  }
}
