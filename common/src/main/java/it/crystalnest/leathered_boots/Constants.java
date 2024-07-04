package it.crystalnest.leathered_boots;

import com.google.common.base.Suppliers;
import it.crystalnest.cobweb.api.pack.DynamicDataPack;
import it.crystalnest.cobweb.api.pack.DynamicTagBuilder;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Common shared constants across all loaders.
 */
@ApiStatus.Internal
public final class Constants {
  /**
   * Mod id.
   */
  public static final String MOD_ID = "leathered_boots";

  /**
   * ID for leathered boots creative mod tab.
   */
  public static final ResourceLocation LEATHERED_BOOTS_TAB_ID = new ResourceLocation(Constants.MOD_ID, "leathered_boots_tab");

  /**
   * Leathered boots dynamic datapack.<br />
   * Adds all leathered boots to {@link ItemTags#FREEZE_IMMUNE_WEARABLES}.
   */
  public static final DynamicDataPack LEATHERED_BOOTS_DYNAMIC_DATA_PACK = (DynamicDataPack) DynamicDataPack
    .named(new ResourceLocation(MOD_ID, "leathered_boots"))
    .add(() -> DynamicTagBuilder.of(Registry.ITEM, ItemTags.FREEZE_IMMUNE_WEARABLES).addElements(LeatheredBootsManager.getBoots()));

  /**
   * Leathered boots trade offers.
   */
  public static final Supplier<Map<Integer, MerchantOffer>> LEATHERED_BOOTS_TRADES = Suppliers.memoize(() -> Map.ofEntries(
    Map.entry(3, new MerchantOffer(new ItemStack(Items.EMERALD, 4), Objects.requireNonNull(LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_CHAIN)), 5, 6, 0.02F)),
    Map.entry(4, new MerchantOffer(new ItemStack(Items.EMERALD, 9), Objects.requireNonNull(LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_IRON)), 3, 10, 0.02F)),
    Map.entry(5, new MerchantOffer(new ItemStack(Items.EMERALD, 13), Objects.requireNonNull(LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_DIAMOND)), 1, 30, 0.02F))
  ));

  /**
   * Mod logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  private Constants() {}
}
