package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
  /**
   * {@link Item Items} {@link DeferredRegister deferred register}.
   */
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeatheredBootsLoader.MODID);

  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_CHAIN Chain}.
   */
  public static final RegistryObject<LeatheredBootsItem> CHAINMAIL_LEATHERED_BOOTS = registerLeatheredBootsItem("chainmail", LeatheredArmorMaterial.LEATHERED_CHAIN);
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_IRON Iron}.
   */
  public static final RegistryObject<LeatheredBootsItem> IRON_LEATHERED_BOOTS = registerLeatheredBootsItem("iron", LeatheredArmorMaterial.LEATHERED_IRON);
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_GOLD Gold}.
   */
  public static final RegistryObject<LeatheredBootsItem> GOLDEN_LEATHERED_BOOTS = registerLeatheredBootsItem("golden", LeatheredArmorMaterial.LEATHERED_GOLD);
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_DIAMOND Diamond}.
   */
  public static final RegistryObject<LeatheredBootsItem> DIAMOND_LEATHERED_BOOTS = registerLeatheredBootsItem("diamond", LeatheredArmorMaterial.LEATHERED_DIAMOND);
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_NETHERITE Netherite}.
   */
  public static final RegistryObject<LeatheredBootsItem> NETHERITE_LEATHERED_BOOTS = registerLeatheredBootsItem("netherite", LeatheredArmorMaterial.LEATHERED_NETHERITE, true);

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    ITEMS.register(bus);
  }

  /**
   * Returns the registered {@link LeatheredBootsItem}.
   * 
   * @param idPrefix prefix for the ID.
   * @param armorMaterial {@link ArmorMaterial} the boots are made of.
   * @param isFireResistant whether the boots are fire resistant.
   * @return the registered {@link LeatheredBootsItem}.
   */
  private static final RegistryObject<LeatheredBootsItem> registerLeatheredBootsItem(String idPrefix, ArmorMaterial armorMaterial, boolean isFireResistant) {
    return ITEMS.register(idPrefix + "_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, () -> new LeatheredBootsItem(armorMaterial, isFireResistant));
  }

  /**
   * Returns the registered {@link LeatheredBootsItem}.
   * 
   * @param idPrefix prefix for the ID.
   * @param armorMaterial {@link ArmorMaterial} the boots are made of.
   * @return the registered {@link LeatheredBootsItem}.
   */
  private static final RegistryObject<LeatheredBootsItem> registerLeatheredBootsItem(String idPrefix, ArmorMaterial armorMaterial) {
    return registerLeatheredBootsItem(idPrefix, armorMaterial, false);
  }
}
