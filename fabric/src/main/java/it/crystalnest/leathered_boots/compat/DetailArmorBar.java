package it.crystalnest.leathered_boots.compat;

import com.redlimerl.detailab.api.DetailArmorBarAPI;
import com.redlimerl.detailab.api.render.ArmorBarRenderManager;
import com.redlimerl.detailab.api.render.TextureOffset;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

import static com.redlimerl.detailab.DetailArmorBar.GUI_ARMOR_BAR;
import static com.redlimerl.detailab.DetailArmorBar.isVanillaTexture;

/**
 * Detail Armor Bar compatibility.
 */
public final class DetailArmorBar {
  private DetailArmorBar() {}

  /**
   * Register base Leathered Boots to Detail Armor Bar.
   */
  @ApiStatus.Internal
  public static void register() {
    register(
      armorMaterial -> {
        int offsetFullX, offsetHalfX, offsetY = 9 + isVanillaTexture();
        if (armorMaterial == LeatheredArmorMaterial.LEATHERED_CHAIN) {
          offsetFullX = 81;
          offsetHalfX = 72;
        } else if (armorMaterial == LeatheredArmorMaterial.LEATHERED_IRON) {
          offsetFullX = 63;
          offsetHalfX = 54;
        } else if (armorMaterial == LeatheredArmorMaterial.LEATHERED_GOLD) {
          offsetFullX = 99;
          offsetHalfX = 90;
        } else if (armorMaterial == LeatheredArmorMaterial.LEATHERED_DIAMOND) {
          offsetFullX = 27;
          offsetHalfX = 18;
        } else if (armorMaterial == LeatheredArmorMaterial.LEATHERED_NETHERITE) {
          offsetFullX = 9;
          offsetHalfX = 0;
        } else {
          Constants.LOGGER.error("An error occurred while attempting to register Leathered Boots {} to Detail Armor Bar:\nUnknown armor type: {}", LeatheredBoots.getKeyFor(Constants.MOD_ID, armorMaterial), armorMaterial);
          offsetFullX = -1;
          offsetHalfX = -1;
        }
        return new ArmorBarTexture(GUI_ARMOR_BAR, 128, 128, offsetFullX, offsetY, offsetHalfX, offsetY, 9, 0, 27, 0);
      },
      LeatheredBoots.getLeatheredBoots(Constants.MOD_ID).toArray(LeatheredBootsItem[]::new)
    );
  }

  /**
   * Register custom Leathered Boots to Detail Armor Bar.
   *
   * @param armorBarTexture function that returns an {@link ArmorBarRenderManager} based on the {@link LeatheredArmorMaterial}.
   * @param leatheredBoots {@link LeatheredBootsItem}s to register.
   */
  public static void register(Function<LeatheredArmorMaterial, ArmorBarTexture> armorBarTexture, LeatheredBootsItem... leatheredBoots) {
    DetailArmorBarAPI.customArmorBarBuilder()
      .armor(leatheredBoots)
      .render(stack -> {
        ArmorBarTexture texture = armorBarTexture.apply(((LeatheredBootsItem) stack.getItem()).getMaterial());
        return new ArmorBarRenderManager(texture.texture, texture.textureWidth, texture.textureHeight, texture.offsetFull(), texture.offsetHalf(), texture.offsetOutline(), texture.offsetOutlineHalf());
      })
      .register();
  }

  /**
   * Wrapper around {@link ArmorBarRenderManager} to provide external compatibility when loading classes from outside.
   *
   * @param texture texture reference.
   * @param textureWidth texture width in pixels.
   * @param textureHeight texture height in pixels.
   * @param offsetFullX offset (in pixels) from the right to the start of the full armor icon.
   * @param offsetFullY offset (in pixels) from the top to the start of the full armor icon.
   * @param offsetHalfX offset (in pixels) from the right to the start of the half armor icon.
   * @param offsetHalfY offset (in pixels) from the top to the start of the half armor icon.
   * @param offsetOutlineX offset (in pixels) from the right to the full outline icon.
   * @param offsetOutlineY offset (in pixels) from the top to the full outline icon.
   * @param offsetOutlineHalfX offset (in pixels) from the right to the half outline icon.
   * @param offsetOutlineHalfY offset (in pixels) from the top to the half outline icon.
   */
  public record ArmorBarTexture(ResourceLocation texture, int textureWidth, int textureHeight, int offsetFullX, int offsetFullY, int offsetHalfX, int offsetHalfY, int offsetOutlineX, int offsetOutlineY, int offsetOutlineHalfX, int offsetOutlineHalfY) {
    /**
     * Full texture offset.
     *
     * @return {@link TextureOffset}.
     */
    public TextureOffset offsetFull() {
      return new TextureOffset(offsetFullX, offsetFullY);
    }

    /**
     * Half texture offset.
     *
     * @return {@link TextureOffset}.
     */
    public TextureOffset offsetHalf() {
      return new TextureOffset(offsetHalfX, offsetHalfY);
    }

    /**
     * Outline texture offset.
     *
     * @return {@link TextureOffset}.
     */
    public TextureOffset offsetOutline() {
      return new TextureOffset(offsetOutlineX, offsetOutlineY);
    }

    /**
     * Half outline texture offset.
     *
     * @return {@link TextureOffset}.
     */
    public TextureOffset offsetOutlineHalf() {
      return new TextureOffset(offsetOutlineHalfX, offsetOutlineHalfY);
    }
  }
}
