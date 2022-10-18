package crystalspider.leatheredboots.config;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue; 

/**
 * Leathered Boots Configuration.
 */
public class LeatheredBootsConfig {
  /**
   * {@link ForgeConfigSpec} {@link ForgeConfigSpec.Builder Builder}.
   */
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  /**
   * Common Configuration as read from the configuration file.
   */
	public static final CommonConfig COMMON = new CommonConfig(BUILDER);
  /**
   * {@link ForgeConfigSpec}.
   */
	public static final ForgeConfigSpec SPEC = BUILDER.build();

  /**
   * Returns the value of {@link CommonConfig#directHitDuration}.
   *
   * @return {@link CommonConfig#directHitDuration} as read from the {@link #COMMON common} configuration file.
   */
  public static Integer getDirectHitDuration() {
		return COMMON.directHitDuration.get();
	}

  /**
   * Common Configuration for Leathered Boots.
   */
  public static class CommonConfig {
    /**
     * Fire Aspect Duration for Direct Hits.
     */
    private final IntValue directHitDuration;

    /**
     * Defines the configuration options, their default values and their comments.
     *
     * @param builder
     */
		public CommonConfig(ForgeConfigSpec.Builder builder) {
      int maxDuration = Enchantments.FIRE_ASPECT.getMaxLevel() * 4;
			directHitDuration = builder.comment("Fire damage duration for direct (main hand) hits.").defineInRange("direct hit duration", 4, 1, maxDuration);
		}
	}
}
