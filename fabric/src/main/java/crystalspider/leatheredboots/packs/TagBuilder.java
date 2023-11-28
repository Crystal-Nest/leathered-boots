package crystalspider.leatheredboots.packs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;

import crystalspider.leatheredboots.ModLoader;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.tag.Tag.Builder;
import net.minecraft.tag.Tag.TrackedEntry;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Tag builder.
 */
@SuppressWarnings("unchecked")
public class TagBuilder<T> extends Builder {
  /**
   * Set of unique listed keys.
   */
  private final Set<String> uniqueKeys = new HashSet<>();
  /**
   * Tags location.
   */
  private final Identifier location;
  /**
   * {@link Registry Minecraft Registries} for the tag.
   */
  private final Registry<T> registries;

  private final String source = ModLoader.MOD_ID + "_dynamic_datapack";

  /**
   * @param location {@link #location}.
   * @param registries {@link #registries}.
   */
  private TagBuilder(Identifier location, Registry<T> registries) {
    this.location = location;
    this.registries = registries;
  }

  /**
   * Creates a new tag builder for the given tag.
   * 
   * @param <T>
   * @param key
   * @param registries
   * @return new tag builder for the given tag.
   */
  public static <T> TagBuilder<T> of(TagKey<? extends T> key, Registry<T> registries) {
    return new TagBuilder<T>(key.id(), registries);
  }

  /**
   * Returns the location of the tag.
   * 
   * @return tag location.
   */
  public Identifier getLocation() {
    return location;
  }

  /**
   * Returns the full path for the tag location.
   * 
   * @return tag full path.
   */
  public Identifier getFullPath() {
    return DataResourceType.TAGS.getPath(new Identifier(getLocation().getNamespace(), Registry.ITEM.getKey().getValue().getPath() + "s/" + getLocation().getPath()));
  }

  @Override
  public TagBuilder<T> add(TrackedEntry entry) {
    if (unique(entry)) {
      return (TagBuilder<T>) super.add(entry);
    }
    return this;
  }

  /**
   * Adds a new tag to be built.
   * 
   * @param tagKey
   * @return this builder.
   */
  public TagBuilder<T> addTag(TagKey<? extends T> tagKey) {
    return (TagBuilder<T>) addTag(tagKey.id(), source);
  }

  /**
   * Adds a new element to be added to the tag.
   * 
   * @param element
   * @return this builder.
   */
  public TagBuilder<T> addElement(T element) {
    return (TagBuilder<T>) add(registries.getKey(element).get().getValue(), source);
  }

  /**
   * Adds new elements to be added to the tag.
   * 
   * @param elements
   * @return this builder.
   */
  public TagBuilder<T> addElements(Collection<? extends T> elements) {
    for (T element : elements) {
      addElement(element);
    }
    return this;
  }

  /**
   * JSON data for the tag.
   * 
   * @return JSON data for the tag.
   */
  public JsonElement json() {
    return toJson();
  }

  /**
   * Checks if the given {@link TagEntry} is already listed and, if not, adds it to the entries to build.
   * 
   * @param entry
   * @return whether the {@link TagEntry} was already listed.
   */
  private boolean unique(TrackedEntry entry){
    if (uniqueKeys.contains(entry.toString())) {
      return false;
    }
    uniqueKeys.add(entry.toString());
    return true;
  }
}
