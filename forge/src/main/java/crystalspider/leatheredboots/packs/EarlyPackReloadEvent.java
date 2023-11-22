package crystalspider.leatheredboots.packs;

import java.util.List;

import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

public record EarlyPackReloadEvent(List<PackResources> packs, ResourceManager manager, PackType type) {}
