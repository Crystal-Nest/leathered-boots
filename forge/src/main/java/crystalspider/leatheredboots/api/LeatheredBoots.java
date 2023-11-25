package crystalspider.leatheredboots.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Leathered Boots API.
 */
public final class LeatheredBoots {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();
  
  /**
   * {@link Item}s {@link DeferredRegister}.
   */
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModLoader.MOD_ID);

  static {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  private LeatheredBoots() {}

  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static volatile ConcurrentHashMap<ResourceLocation, RegistryObject<LeatheredBootsItem>> leatheredBoots = new ConcurrentHashMap<>();

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   * 
   * @param modId
   * @param isFireResistant
   * @param armorMaterial
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String modId, boolean isFireResistant, ArmorMaterial armorMaterial) {
    LeatheredArmorMaterial leatheredArmorMaterial = armorMaterial instanceof LeatheredArmorMaterial leathered ? leathered : new LeatheredArmorMaterial(armorMaterial);
    ResourceLocation key = getKeyFor(leatheredArmorMaterial);
    if (!leatheredBoots.containsKey(key)) {
      leatheredBoots.put(key, ITEMS.register(key.getPath(), () -> new LeatheredBootsItem(leatheredArmorMaterial, isFireResistant)));
      return key;
    }
    LOGGER.error("LeatheredBootsItem [" + key + "] was already registered with the following value: " + leatheredBoots.get(key));
    return null;
  }

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial} setting fire resistance to {@code false}.
   * 
   * @param modId
   * @param armorMaterial
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String modId, ArmorMaterial armorMaterial) {
    return registerLeatheredBoots(modId, false, armorMaterial);  
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param modId
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
   * @param modId
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. A {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String modId, List<ArmorMaterial> armorMaterials) {
    return registerLeatheredBoots(modId, false, armorMaterials);
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param modId
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
   * @param modId
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
    return leatheredBoots.values().stream().map(item -> item.get()).toList();
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
   * Returns the {@link LeatheredBootsItem} registered with the given {@code modId} and {@code itemId}.
   * 
   * @param modId
   * @param itemId
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getLeatheredBoots(String modId, String itemId) {
    return getLeatheredBoots(new ResourceLocation(modId, itemId));
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getLeatheredBoots(ArmorMaterial armorMaterial) {
    return getLeatheredBoots(getKeyFor(armorMaterial));
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
   * @param modId
   * @param itemId
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String modId, String itemId) {
    return getLeatheredBootsStack(new ResourceLocation(modId, itemId));
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(ArmorMaterial armorMaterial) {
    return getLeatheredBootsStack(getKeyFor(armorMaterial));
  }

  /**
   * Returns the list of all registered mod ids.
   * 
   * @return the list of all registered mod ids.
   */
  public static List<String> getModIds() {
    return leatheredBoots.keySet().stream().map(resourceLocation -> resourceLocation.getNamespace()).toList();
  }

  /**
   * Returns the {@link ResourceLocation} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.
   * <p>
   * Note: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   * 
   * @param armorMaterial
   * @return {@link ResourceLocation} for a {@link LeatheredBootsItem}.
   */
  public static ResourceLocation getKeyFor(ArmorMaterial armorMaterial) {
    return new ResourceLocation(ModLoader.MOD_ID, (armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial)).getName() + "_boots");
  }
}
