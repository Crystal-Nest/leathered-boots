package it.crystalnest.leathered_boots;

import it.crystalnest.cobweb.api.pack.DynamicDataPack;
import it.crystalnest.cobweb.api.pack.DynamicTagBuilder;
import it.crystalnest.leathered_boots.api.LeatheredBoots;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  public static final DynamicDataPack LEATHERED_BOOTS_DYNAMIC_DATA_PACK = (DynamicDataPack) DynamicDataPack
    .named(new ResourceLocation(MOD_ID, "freeze_immune_boots"))
    .add(() -> DynamicTagBuilder
      .of(Registries.ITEM, ItemTags.TRIMMABLE_ARMOR, ItemTags.FREEZE_IMMUNE_WEARABLES)
      .addElements(LeatheredBoots.getLeatheredBoots())
    );

  /**
   * Mod logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  private Constants() {}
}
