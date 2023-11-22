package crystalspider.leatheredboots.packs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class TagBuilder extends net.minecraft.tags.TagBuilder {
  private final Set<String> uniqueKeys = new HashSet<>();
  private final ResourceLocation location;

  // TODO: Allow building multiple tags with a single TagBuilder.

  private TagBuilder(ResourceLocation location) {
    this.location = location;
  }

  public static TagBuilder of(TagKey<?> key) {
    return new TagBuilder(key.location());
  }

  public ResourceLocation getLocation() {
    return location;
  }

  @Override
  public TagBuilder add(TagEntry entry) {
    if (unique(entry)) {
      return (TagBuilder) super.add(entry);
    }
    return this;
  }

  public TagBuilder addTag(TagKey<?> tagKey) {
    return (TagBuilder) addTag(tagKey.location());
  }

  public <T extends Item> TagBuilder addElement(T element) {
    return (TagBuilder) addElement(ForgeRegistries.ITEMS.getKey(element));
  }

  public void addJson(JsonObject oldTag) {
    TagFile.CODEC.parse(new Dynamic<>(JsonOps.INSTANCE, oldTag)).getOrThrow(false, error -> {}).entries().forEach(this::add);
  }

  public TagBuilder addElements(Collection<? extends Item> elements) {
    for (Item element : elements) {
      addElement(element);
    }
    return this;
  }

  public JsonElement json() {
    return TagFile.CODEC.encodeStart(JsonOps.INSTANCE, new TagFile(build(), false)).getOrThrow(false, error -> {});
  }

  private boolean unique(TagEntry entry){
    if (uniqueKeys.contains(entry.toString())) {
      return false;
    }
    uniqueKeys.add(entry.toString());
    return true;
  }
}
