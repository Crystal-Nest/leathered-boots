package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.armor.LeatheredArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class ItemRegistry {
  private static final Register<Item> register = LeatheredBootsLoader.register.of(Registries.ITEM);

  public static Item CHAINMAIL_LEATHERED_BOOTS = register.apply("chainmail_" + LeatheredBootsLoader.LEATHERED_BOOTS_ID, new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_CHAIN, false));
}
