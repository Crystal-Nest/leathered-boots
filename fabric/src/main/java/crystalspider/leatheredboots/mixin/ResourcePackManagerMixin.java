package crystalspider.leatheredboots.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourceType;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Injects into {@link ResourcePackManager} to alter the datapack providers.
 */
@Mixin(ResourcePackManager.class)
public abstract class ResourcePackManagerMixin {
  /**
   * Modifies the second argument passed to the {@link ResourcePackManager} constructor in the constructor {@link ResourcePackManager#ResourcePackManager(ResourceType, ResourcePackProvider...)}.
   * <p>
   * Adds the dynamic datapack provider.
   * 
   * @param initials
   * @return original list of providers with the addition of a custom one.
   */
  @ModifyArg(method = "<init>(Lnet/minecraft/resource/ResourceType;[Lnet/minecraft/resource/ResourcePackProvider;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourcePackProfile$Factory;[Lnet/minecraft/resource/ResourcePackProvider;)V"), index = 1)
	private static ResourcePackProvider[] onInit(ResourcePackProvider[] initials) {
    ResourcePackProvider[] providers = new ResourcePackProvider[initials.length + 1];
    for (int i = 0; i < initials.length; i++) {
      providers[i] = initials[i];
    }
    providers[initials.length] = (infoConsumer, packFactory) -> infoConsumer.accept(
      new DynamicDatapack(
        new Identifier(ModLoader.MOD_ID, "freeze_immune_boots"),
        TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES, Registry.ITEM).addElements(LeatheredBoots.getLeatheredBoots())
      ).create()
    );
    return providers;
	}
}
