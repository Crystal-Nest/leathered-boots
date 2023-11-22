package crystalspider.leatheredboots.mixin;

import java.util.List;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import crystalspider.leatheredboots.packs.EarlyPackReloadEvent;
import crystalspider.leatheredboots.packs.MoonlightEventsHelper;

import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import net.minecraft.server.packs.resources.Resource;

@Mixin(MultiPackResourceManager.class)
public abstract class MultiPackResourceManagerMixin implements CloseableResourceManager {
  @Shadow
  public abstract Optional<Resource> getResource(ResourceLocation arg);

  @Inject(method = "<init>", at = @At(value = "RETURN"))
  private void dynamicPackEarlyReload(PackType type, List<PackResources> packs, CallbackInfo cir) {
    MoonlightEventsHelper.postEvent(new EarlyPackReloadEvent(packs, this, type), EarlyPackReloadEvent.class);
  }
}

