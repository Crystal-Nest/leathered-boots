package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemRegistry {
  /**
   * ID for leathered boots creative mod tab.
   */
  public static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  /**
   * {@link CreativeModTab} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  static final CreativeModeTab LEATHERED_BOOTS_TAB = new CreativeModeTab(ModLoader.MOD_ID + "." + LEATHERED_BOOTS_TAB_ID) {
    @Override
    public ItemStack makeIcon() {
      return LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE);
    }
  }.setRecipeFolderName(LEATHERED_BOOTS_TAB_ID);

  /**
   * Registers all mod items.
   */
  public static void register() {
    LeatheredBoots.registerLeatheredBoots(
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
