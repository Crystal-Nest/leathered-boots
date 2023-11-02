package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.api.Register;
import crystalspider.leatheredboots.armor.LeatheredArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class ItemRegistry {
  private static final Register<Item> REGISTER = LeatheredBootsLoader.REGISTER_PROVIDER.of(Registries.ITEM);

  public static final TestItem TEST = new TestItem();

  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_CHAIN Chain}.
   */
  public static final LeatheredBootsItem CHAINMAIL_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_CHAIN, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_IRON Iron}.
   */
  public static final LeatheredBootsItem IRON_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_IRON, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_GOLD Gold}.
   */
  public static final LeatheredBootsItem GOLDEN_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_GOLD, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_DIAMOND Diamond}.
   */
  public static final LeatheredBootsItem DIAMOND_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_DIAMOND, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_NETHERITE Netherite}.
   */
  public static final LeatheredBootsItem NETHERITE_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_NETHERITE, true);

  public static final register() {
    REGISTER.apply("chainmail_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, TEST);

    REGISTER.apply("chainmail_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, CHAINMAIL_LEATHERED_BOOTS);
    REGISTER.apply("iron_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, IRON_LEATHERED_BOOTS);
    REGISTER.apply("golden_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, GOLDEN_LEATHERED_BOOTS);
    REGISTER.apply("diamond_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, DIAMOND_LEATHERED_BOOTS);
    REGISTER.apply("netherite_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, NETHERITE_LEATHERED_BOOTS);
  }
}
