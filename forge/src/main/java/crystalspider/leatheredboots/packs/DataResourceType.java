package crystalspider.leatheredboots.packs;

import net.minecraft.resources.ResourceLocation;

/**
 * Datapack resource type.
 */
public enum DataResourceType {
  GENERIC("%s"),
  TAGS("tags/%s.json"),
  RECIPES("recipes/%s.json"),
  ADVANCEMENTS("advancements/%s.json"),
  ITEM_MODELS("models/item/%s.json");

  /**
   * Location pattern.
   */
  private final String location;

  /**
   * @param location {@link #location}.
   */
  DataResourceType(String location){
    this.location = location;
  }

  /**
   * Returns the proper path for the resource identified by the given {@link ResourceLocation} with respect to the kind of resource type.
   * 
   * @param relativeLocation
   * @return proper resource path.
   */
  public ResourceLocation getPath(ResourceLocation relativeLocation) {
    return new ResourceLocation(relativeLocation.getNamespace(), String.format(this.location, relativeLocation.getPath()));
  }
}
