package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.api.Register;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ItemRegistry {
  private static final Register<Item> REGISTER = LeatheredBootsLoader.REGISTER_PROVIDER.of(Registries.ITEM);

  /**
   * Base ID for leathered boots.
   */
  public static final String LEATHERED_BOOTS_ID = "leathered_boots";

    /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = FabricItemGroup
    .builder(new Identifier(LeatheredBootsLoader.MODID, LEATHERED_BOOTS_ID))
    .icon(() -> ItemRegistry.NETHERITE_LEATHERED_BOOTS.getDefaultStack())
    .build();

  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_CHAIN Chain}.
   */
  public static final LeatheredBootsItem CHAINMAIL_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_CHAIN);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_IRON Iron}.
   */
  public static final LeatheredBootsItem IRON_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_IRON);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_GOLD Gold}.
   */
  public static final LeatheredBootsItem GOLDEN_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_GOLD);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_DIAMOND Diamond}.
   */
  public static final LeatheredBootsItem DIAMOND_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_DIAMOND);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_NETHERITE Netherite}.
   */
  public static final LeatheredBootsItem NETHERITE_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_NETHERITE, true);

  public static final void register() {
    REGISTER.apply("chainmail_" + LEATHERED_BOOTS_ID, CHAINMAIL_LEATHERED_BOOTS);
    REGISTER.apply("iron_" + LEATHERED_BOOTS_ID, IRON_LEATHERED_BOOTS);
    REGISTER.apply("golden_" + LEATHERED_BOOTS_ID, GOLDEN_LEATHERED_BOOTS);
    REGISTER.apply("diamond_" + LEATHERED_BOOTS_ID, DIAMOND_LEATHERED_BOOTS);
    REGISTER.apply("netherite_" + LEATHERED_BOOTS_ID, NETHERITE_LEATHERED_BOOTS);
  }
}
