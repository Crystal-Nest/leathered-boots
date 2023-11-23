package crystalspider.leatheredboots.packs;

import net.minecraft.util.Identifier;

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
   * Returns the proper path for the resource identified by the given {@link Identifier} with respect to the kind of resource type.
   * 
   * @param relativeLocation
   * @return proper resource path.
   */
  public Identifier getPath(Identifier relativeLocation) {
    return new Identifier(relativeLocation.getNamespace(), String.format(this.location, relativeLocation.getPath()));
  }
}
