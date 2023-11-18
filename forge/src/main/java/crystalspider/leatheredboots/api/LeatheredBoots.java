package crystalspider.leatheredboots.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LeatheredBoots {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();
  
  /**
   * {@link Item Items} {@link DeferredRegister deferred register}.
   */
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModLoader.MOD_ID);

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    ITEMS.register(bus);
  }

  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static volatile ConcurrentHashMap<ResourceLocation, RegistryObject<LeatheredBootsItem>> leatheredBoots = new ConcurrentHashMap<>();

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   * 
   * @param idPrefix
   * @param armorMaterial
   * @param isFireResistant
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String idPrefix, ArmorMaterial armorMaterial, boolean isFireResistant) {
    ResourceLocation key = getKeyFor(idPrefix, armorMaterial);
    if (!leatheredBoots.containsKey(key)) {
      RegistryObject<LeatheredBootsItem> value = ITEMS.register(idPrefix + "_" + ModLoader.LEATHERED_BOOTS_ID, () -> new LeatheredBootsItem(armorMaterial, isFireResistant));
      leatheredBoots.put(key, value);
      return key;
    }
    LOGGER.error("ArmorMaterial [" + key + "] was already registered with the following value: " + leatheredBoots.get(key).get());
    return null;
  }

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial} setting fire resistance to {@code false}.
   * 
   * @param modId
   * @param armorMaterial
   * @return the {@link ResourceLocation} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized ResourceLocation registerLeatheredBoots(String idPrefix, ArmorMaterial armorMaterial) {
    return registerLeatheredBoots(idPrefix, armorMaterial, false);  
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param modId
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. An {@link ResourceLocation} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String idPrefix, List<ImmutablePair<ArmorMaterial, Boolean>> armorMaterials) {
    List<ResourceLocation> identifiers = new ArrayList<>();
    for (ImmutablePair<ArmorMaterial, Boolean> pair : armorMaterials) {
      identifiers.add(registerLeatheredBoots(idPrefix, pair.getLeft(), pair.getRight()));
    }
    return identifiers;
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param IsPrefix
   * @param armorMaterials
   * @return the {@link ResourceLocation}s of the newly registered {@link LeatheredBootsItem}s. An {@link Identifier} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<ResourceLocation> registerLeatheredBoots(String idPrefix, ImmutablePair<ArmorMaterial, Boolean>... armorMaterials) {
    return registerLeatheredBoots(idPrefix, Arrays.asList(armorMaterials));
  }

  /**
   * Returns the list of all registered {@link LeatheredBootsItem}s.
   * 
   * @return the list of all registered {@link LeatheredBootsItem}s.
   */
  public static List<RegistryObject<LeatheredBootsItem>> getLeatheredBoots() {
    return leatheredBoots.values().stream().toList();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ResourceLocation}.
   * 
   * @param identifier
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static RegistryObject<LeatheredBootsItem> getLeatheredBoots(ResourceLocation resourceLocation) {
    return leatheredBoots.get(resourceLocation);
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@code IsPrefix} and {@code itemId}.
   * 
   * @param IsPrefix
   * @param itemId
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static RegistryObject<LeatheredBootsItem> getLeatheredBoots(String idPrefix, String itemId) {
    return getLeatheredBoots(new ResourceLocation(idPrefix, itemId));
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static RegistryObject<LeatheredBootsItem> getLeatheredBoots(String idPrefix, ArmorMaterial armorMaterial) {
    return getLeatheredBoots(getKeyFor(idPrefix, armorMaterial));
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
    RegistryObject<LeatheredBootsItem> item = getLeatheredBoots(resourceLocation);
    if (item != null) {
      return item.get().getDefaultInstance();
    }
    return null;
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link Identifier}.
   * 
   * @param IsPrefix
   * @param itemId
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String IsPrefix, String itemId) {
    return getLeatheredBootsStack(new ResourceLocation(IsPrefix, itemId));
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link ArmorMaterial}.
   * 
   * @param armorMaterial
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String idPrefix, ArmorMaterial armorMaterial) {
    return getLeatheredBootsStack(getKeyFor(idPrefix, armorMaterial));
  }

  /**
   * Returns the list of all registered mod ids.
   * 
   * @return the list of all registered mod ids.
   */
  public static List<String> getIsPrefixs() {
    return leatheredBoots.keySet().stream().map(identifier -> identifier.getNamespace()).toList();
  }

  /**
   * Get the {@link ResourceLocation} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.
   * <p>
   * Note: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   * 
   * @param armorMaterial
   * @return {@link ResourceLocation} for a {@link LeatheredBootsItem}.
   */
  public static ResourceLocation getKeyFor(String idPrefix, ArmorMaterial armorMaterial) {
    return new ResourceLocation(idPrefix + "_" + ModLoader.LEATHERED_BOOTS_ID, (armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial)).getName());
  }

  /**
   * Get the {@link ResourceLocation} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.
   * <p>
   * Note: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   * 
   * @param armorMaterial
   * @return {@link ResourceLocation} for a {@link LeatheredBootsItem}.
   */
  public static ResourceLocation getKeyFor(String idPrefix, String itemId) {
    return new ResourceLocation(idPrefix + "_" + ModLoader.LEATHERED_BOOTS_ID, itemId);
  }
}
