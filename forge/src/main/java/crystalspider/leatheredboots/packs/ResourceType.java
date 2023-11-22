package crystalspider.leatheredboots.packs;

import net.minecraft.resources.ResourceLocation;

public enum ResourceType {
  GENERIC("%s"),
  TAGS("tags/%s.json"),
  RECIPES("recipes/%s.json"),
  ADVANCEMENTS("advancements/%s.json"),
  MODELS("models/%s.json"),
  ITEM_MODELS("models/item/%s.json");

  private final String location;

  ResourceType(String location){
    this.location = location;
  }

  public ResourceLocation getPath(ResourceLocation relativeLocation) {
    return new ResourceLocation(relativeLocation.getNamespace(), String.format(this.location, relativeLocation.getPath()));
  }

  public ResourceLocation getPath(String relativeLocation) {
    return this.getPath(new ResourceLocation(relativeLocation));
  }
}
