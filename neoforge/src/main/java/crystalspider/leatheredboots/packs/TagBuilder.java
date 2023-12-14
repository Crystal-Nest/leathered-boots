package crystalspider.leatheredboots.packs;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;

/**
 * Tag builder.
 */
@SuppressWarnings("unchecked")
public class TagBuilder<T> extends net.minecraft.tags.TagBuilder {
  /**
   * Set of unique listed keys.
   */
  private final Set<String> uniqueKeys = new HashSet<>();
  /**
   * Tags location.
   */
  private final ResourceLocation location;
  /**
   * {@link DefaultedRegistry} for the tag.
   */
  private final DefaultedRegistry<T> registries;

  /**
   * @param location {@link #location}.
   * @param registries {@link #registries}.
   */
  private TagBuilder(ResourceLocation location, DefaultedRegistry<T> registries) {
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
  public static <T> TagBuilder<T> of(TagKey<? extends T> key, DefaultedRegistry<T> registries) {
    return new TagBuilder<T>(key.location(), registries);
  }

  /**
   * Returns the location of the tag.
   * 
   * @return tag location.
   */
  public ResourceLocation getLocation() {
    return location;
  }

  /**
   * Returns the full path for the tag location.
   * 
   * @return tag full path.
   */
  public ResourceLocation getFullPath() {
    return DataResourceType.TAGS.getPath(new ResourceLocation(getLocation().getNamespace(), BuiltInRegistries.ITEM.key().location().getPath() + "s/" + getLocation().getPath()));
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
    return (TagBuilder<T>) addTag(tagKey.location());
  }

  /**
   * Adds a new element to be added to the tag.
   * 
   * @param element
   * @return this builder.
   */
  public TagBuilder<T> addElement(T element) {
    return (TagBuilder<T>) addElement(registries.getKey(element));
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
    return TagFile.CODEC.encodeStart(JsonOps.INSTANCE, new TagFile(build(), false, List.of())).getOrThrow(false, error -> {});
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
