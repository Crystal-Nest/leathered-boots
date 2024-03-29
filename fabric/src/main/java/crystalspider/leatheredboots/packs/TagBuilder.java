package crystalspider.leatheredboots.packs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagEntry;
import net.minecraft.registry.tag.TagFile;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * Tag builder.
 */
@SuppressWarnings("unchecked")
public class TagBuilder<T> extends net.minecraft.registry.tag.TagBuilder {
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
    return DataResourceType.TAGS.getPath(new Identifier(getLocation().getNamespace(), Registries.ITEM.getKey().getValue().getPath() + "s/" + getLocation().getPath()));
  }

  @Override
  public TagBuilder<T> add(TagEntry entry) {
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
    return (TagBuilder<T>) addTag(tagKey.id());
  }

  /**
   * Adds a new element to be added to the tag.
   * 
   * @param element
   * @return this builder.
   */
  public TagBuilder<T> addElement(T element) {
    return (TagBuilder<T>) add(registries.getKey(element).get().getValue());
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
    return TagFile.CODEC.encodeStart(JsonOps.INSTANCE, new TagFile(build(), false)).getOrThrow(false, error -> {});
  }

  /**
   * Checks if the given {@link TagEntry} is already listed and, if not, adds it to the entries to build.
   * 
   * @param entry
   * @return whether the {@link TagEntry} was already listed.
   */
  private boolean unique(TagEntry entry){
    if (uniqueKeys.contains(entry.toString())) {
      return false;
    }
    uniqueKeys.add(entry.toString());
    return true;
  }
}
