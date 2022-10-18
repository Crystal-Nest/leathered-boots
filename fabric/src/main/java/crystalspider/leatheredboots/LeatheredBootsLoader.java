package crystalspider.leatheredboots;

import crystalspider.leatheredboots.config.LeatheredBootsConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

/**
 * Leathered Boots mod loader.
 */
public class LeatheredBootsLoader implements ModInitializer {
  /**
   * ID of this mod.
   */
  public static final String MODID = "leatheredboots";

  @Override
	public void onInitialize() {
    ModLoadingContext.registerConfig(MODID, ModConfig.Type.COMMON, LeatheredBootsConfig.SPEC);
  }
}