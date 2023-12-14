package crystalspider.leatheredboots.mixin;

import java.nio.file.Path;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.VanillaDataPackProvider;
import net.minecraft.util.Identifier;

/**
 * Injects into {@link VanillaDataPackProvider} to alter the datapack providers.
 */
@Mixin(VanillaDataPackProvider.class)
public abstract class VanillaDataPackProviderMixin {
  /**
   * Modifies the only argument passed to the {@link ResourcePackManager} constructor in the method {@link VanillaDataPackProvider#createManager(Path)}.
   * <p>
   * Adds the dynamic datapack provider.
   * 
   * @param initials
   * @return original list of providers with the addition of a custom one.
   */
  @ModifyArg(method = "createManager(Ljava/nio/file/Path;Lnet/minecraft/util/path/SymlinkFinder;)Lnet/minecraft/resource/ResourcePackManager;", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourcePackManager;<init>([Lnet/minecraft/resource/ResourcePackProvider;)V"))
	private static ResourcePackProvider[] onCreateManager(ResourcePackProvider[] initials) {
    ResourcePackProvider[] providers = new ResourcePackProvider[initials.length + 1];
    for (int i = 0; i < initials.length; i++) {
      providers[i] = initials[i];
    }
    providers[initials.length] = packConsumer -> packConsumer.accept(
      new DynamicDatapack(
        new Identifier(ModLoader.MOD_ID, "freeze_immune_boots"),
        TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES, Registries.ITEM).addElements(LeatheredBoots.getLeatheredBoots()),
        TagBuilder.of(ItemTags.TRIMMABLE_ARMOR, Registries.ITEM).addElements(LeatheredBoots.getLeatheredBoots())
      ).create()
    );
    return providers;
	}
}
