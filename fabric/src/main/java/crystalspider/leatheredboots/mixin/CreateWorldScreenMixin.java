package crystalspider.leatheredboots.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.util.registry.Registry;
import net.minecraft.tag.ItemTags;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.util.Identifier;

/**
 * Injects into {@link CreateWorldScreen} to alter the datapack providers.
 */
@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
  /**
   * Modifies the {@link ResourcePackManager} variable in the method {@link CreateWorldScreen#create(MinecraftClient, Screen)}.
   * <p>
   * Adds the dynamic datapack provider.
   * 
   * @param manager
   * @return the same variable with the added provider.
   */
  @ModifyVariable(method = "create(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/screen/Screen;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;createServerConfig(Lnet/minecraft/resource/ResourcePackManager;Lnet/minecraft/resource/DataConfiguration;)Lnet/minecraft/server/SaveLoading$ServerConfig;"))
	private static ResourcePackManager onCreate(ResourcePackManager manager) {
		manager.providers.add((infoConsumer, packFactory) -> infoConsumer.accept(
      new DynamicDatapack(
        new Identifier(ModLoader.MOD_ID, "freeze_immune_boots"),
        TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES, Registry.ITEM).addElements(LeatheredBoots.getLeatheredBoots())
      ).create()
    ));
		return manager;
	}
}
