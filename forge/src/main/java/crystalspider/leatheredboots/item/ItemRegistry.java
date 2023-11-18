package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_CHAIN Chain}.
   */
  public static final RegistryObject<LeatheredBootsItem> CHAINMAIL_LEATHERED_BOOTS = LeatheredBoots.getLeatheredBoots(LeatheredBoots.registerLeatheredBoots("chainmail", LeatheredArmorMaterial.LEATHERED_CHAIN));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_IRON Iron}.
   */
  public static final RegistryObject<LeatheredBootsItem> IRON_LEATHERED_BOOTS = LeatheredBoots.getLeatheredBoots(LeatheredBoots.registerLeatheredBoots("iron", LeatheredArmorMaterial.LEATHERED_IRON));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_GOLD Gold}.
   */
  public static final RegistryObject<LeatheredBootsItem> GOLDEN_LEATHERED_BOOTS = LeatheredBoots.getLeatheredBoots(LeatheredBoots.registerLeatheredBoots("golden", LeatheredArmorMaterial.LEATHERED_GOLD));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_DIAMOND Diamond}.
   */
  public static final RegistryObject<LeatheredBootsItem> DIAMOND_LEATHERED_BOOTS = LeatheredBoots.getLeatheredBoots(LeatheredBoots.registerLeatheredBoots("diamond", LeatheredArmorMaterial.LEATHERED_DIAMOND));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterial#LEATHERED_NETHERITE Netherite}.
   */
  public static final RegistryObject<LeatheredBootsItem> NETHERITE_LEATHERED_BOOTS = LeatheredBoots.getLeatheredBoots(LeatheredBoots.registerLeatheredBoots("netherite", LeatheredArmorMaterial.LEATHERED_NETHERITE, true));

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    LeatheredBoots.register(bus);
  }
}
