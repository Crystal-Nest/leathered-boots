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
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/**
 * Provides an easy way to register new {@link LeatheredBootsItem}s.
 */
public final class LeatheredBoots {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   * {@link Register} for {@link Item}s.
   */
  private static final Register<Item> REGISTER = ModLoader.REGISTER_PROVIDER.of(Registries.ITEM);

  /**
   * {@link ConcurrentHashMap} of all registered {@link LeatheredBootsItem}s.
   */
  private static volatile ConcurrentHashMap<Identifier, LeatheredBootsItem> leatheredBoots = new ConcurrentHashMap<>();

  /**
   * Registers a new {@link LeatheredBootsItem} made of the given {@link ArmorMaterial}.
   * 
   * @param modId
   * @param armorMaterial
   * @return the {@link Identifier} of the newly registered {@link LeatheredBootsItem} or {@code null}.
   */
  public static synchronized Identifier registerLeatheredBoots(String modId, ArmorMaterial armorMaterial) {
    LeatheredBootsItem value = new LeatheredBootsItem(armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial));
    Identifier key = getKeyFor(armorMaterial);
    if (!leatheredBoots.containsKey(key)) {
      leatheredBoots.put(key, value);
      REGISTER.apply(key.getPath(), value);
      CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(value, CauldronBehavior.CLEAN_DYEABLE_ITEM);
      return key;
    }
    LOGGER.error("ArmorMaterial [" + key + "] was already registered with the following value: " + leatheredBoots.get(key));
    return null;
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param modId
   * @param armorMaterials
   * @return the {@link Identifier}s of the newly registered {@link LeatheredBootsItem}s. An {@link Identifier} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<Identifier> registerLeatheredBoots(String modId, ArmorMaterial... armorMaterials) {
    return registerLeatheredBoots(modId, Arrays.asList(armorMaterials));
  }

  /**
   * Registers new {@link LeatheredBootsItem}s made of the given {@link ArmorMaterial}s.
   * 
   * @param modId
   * @param armorMaterials
   * @return the {@link Identifier}s of the newly registered {@link LeatheredBootsItem}s. An {@link Identifier} can be {@code null} if the registration was unsuccessful.
   */
  public static synchronized List<Identifier> registerLeatheredBoots(String modId, List<ArmorMaterial> armorMaterials) {
    List<Identifier> identifiers = new ArrayList<>();
    for (ArmorMaterial armorMaterial : armorMaterials) {
      identifiers.add(registerLeatheredBoots(modId, armorMaterial));
    }
    return identifiers;
  }

  /**
   * Returns the list of all registered {@link LeatheredBootsItem}s.
   * 
   * @return the list of all registered {@link LeatheredBootsItem}s.
   */
  public static List<LeatheredBootsItem> getLeatheredBoots() {
    return leatheredBoots.values().stream().toList();
  }

  /**
   * Returns the {@link LeatheredBootsItem} registered with the given {@link Identifier}.
   * 
   * @param identifier
   * @return registered {@link LeatheredBootsItem} or {@code null}.
   */
  @Nullable
  public static LeatheredBootsItem getLeatheredBoots(Identifier identifier) {
    return leatheredBoots.get(identifier);
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
    return getLeatheredBoots(new Identifier(modId, itemId));
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
    return leatheredBoots.values().stream().map(item -> item.getDefaultStack()).toList();
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link Identifier}.
   * 
   * @param identifier
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(Identifier identifier) {
    LeatheredBootsItem item = getLeatheredBoots(identifier);
    if (item != null) {
      return item.getDefaultStack();
    }
    return null;
  }

  /**
   * Returns the {@link ItemStack} of the {@link LeatheredBootsItem} registered with the given {@link Identifier}.
   * 
   * @param modId
   * @param itemId
   * @return {@link ItemStack} or {@code null}.
   */
  @Nullable
  public static ItemStack getLeatheredBootsStack(String modId, String itemId) {
    return getLeatheredBootsStack(new Identifier(modId, itemId));
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
    return leatheredBoots.keySet().stream().map(identifier -> identifier.getNamespace()).toList();
  }
  
  /**
   * Get the {@link Identifier} that would be given to a {@link LeatheredBootsItem} of the given {@link ArmorMaterial} when registered.
   * <p>
   * Note: this does not grant that such a {@link LeatheredBootsItem} has been registered.
   * 
   * @param armorMaterial
   * @return {@link Identifier} for a {@link LeatheredBootsItem}.
   */
  public static Identifier getKeyFor(ArmorMaterial armorMaterial) {
    return new Identifier(ModLoader.MOD_ID, (armorMaterial instanceof LeatheredArmorMaterial leatheredArmorMaterial ? leatheredArmorMaterial : new LeatheredArmorMaterial(armorMaterial)).getName() + "_boots");
  }
}
