package crystalspider.leatheredboots.packs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Suppliers;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

import net.minecraft.SharedConstants;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackCompatibility;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProfile.InsertionPosition;
import net.minecraft.resource.ResourcePackProfile.Metadata;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Dynamic Datapack.
 */
public class DynamicDatapack implements ResourcePack {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   * Datapack name.
   */
  private final Identifier name;
  /**
   * Datapack main namespace.
   */
  private final String namespace;
  /**
   * All datapack registered namespaces.
   */
  private final Set<String> namespaces = new HashSet<>();
  /**
   * {@link Supplier} for the datapack metadata.
   */
  private final Supplier<PackResourceMetadata> metadata;
  /**
   * Datapack resources.
   */
  private final Map<Identifier, Supplier<byte[]>> resources = new ConcurrentHashMap<>();

  /**
   * @param name {@link #name}.
   * @param builders list of {@link TagBuilder}s.
   */
  public DynamicDatapack(Identifier name, TagBuilder<?> ...builders) {
    this.name = name;
    this.namespace = name.getNamespace();
    this.namespaces.add(name.getNamespace());
    this.metadata = Suppliers.memoize(()-> new PackResourceMetadata(Text.translatable(namespace + "_dynamic_" + name.getPath()), SharedConstants.getGameVersion().getResourceVersion(ResourceType.SERVER_DATA), Optional.empty()));
    this.build(builders);
  }

  /**
   * Creates a {@link ResourcePackProfile} instance of this dynamic datapack.
   * 
   * @return {@link ResourcePackProfile} instance.
   */
  public ResourcePackProfile create() {
    return ResourcePackProfile.of(
      getName(),
      Text.translatable(getName()),
      true,
      new DynamicPackFactory(this),
      new Metadata(metadata.get().description(), ResourcePackCompatibility.COMPATIBLE, FeatureSet.empty(), List.of()),
      InsertionPosition.TOP,
      false,
      ResourcePackSource.BUILTIN
    );
  }

  @Override
  public String getName() {
    return name.toString();
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public Set<String> getNamespaces(ResourceType packType) {
    return this.namespaces;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T parseMetadata(ResourceMetadataReader<T> serializer) {
    return serializer.getKey().equals(PackResourceMetadata.SERIALIZER.getKey()) ? (T) this.metadata : null;
  }

  @Override
  @Nullable
  public InputSupplier<InputStream> openRoot(String ...strings) {
    return null;
  }

  @Override
  public void findResources(ResourceType packType, String namespace, String id, ResultConsumer output) {
    if (packType == ResourceType.SERVER_DATA && this.namespaces.contains(namespace)) {
      this.resources.entrySet().stream()
        .filter(resource -> (resource.getKey().getNamespace().equals(namespace) && resource.getKey().getPath().startsWith(id)))
        .forEach(resource -> output.accept(resource.getKey(), () -> new ByteArrayInputStream(resource.getValue().get())));
    }
  }

  @Override
  public InputSupplier<InputStream> open(ResourceType type, Identifier id) {
    if (this.resources.containsKey(id)) {
      return () -> {
        if (type == ResourceType.SERVER_DATA) {
          return new ByteArrayInputStream(this.resources.get(id).get());
        }
        throw new IOException(String.format("Tried to access wrong type of resource on %s.", this.name));
      };
    }
    return null;
  }

  @Override
  public void close() {}

  /**
   * Adds the given bytes {@link Supplier} to the ones to build this datapack resources.
   * 
   * @param path
   * @param bytes
   */
  private void buildBytes(Identifier path, Supplier<byte[]> bytes) {
    this.namespaces.add(path.getNamespace());
    this.resources.put(path, Suppliers.memoize(bytes::get));
  }

  /**
   * Adds the given {@link JsonElement} {@link Supplier} to the ones to build this datapack resources.
   * 
   * @param path
   * @param json
   */
  private void buildJson(Identifier path, Supplier<JsonElement> json) {
    this.buildBytes(DataResourceType.GENERIC.getPath(path), () -> {
      try (StringWriter stringWriter = new StringWriter(); JsonWriter jsonWriter = new JsonWriter(stringWriter);) {
        jsonWriter.setIndent("  ");
        Streams.write(json.get(), jsonWriter);
        return stringWriter.toString().getBytes();
      } catch (IOException e) {
        LOGGER.error("Failed to write JSON " + path + " to resource pack " + name + ".", e);
        return new byte[0];
      }
    });
  }

  /**
   * Uses the given {@link TagBuilder}s to get {@link Supplier}s for this datapack resources.
   * 
   * @param builders
   */
  private void build(TagBuilder<?> ...builders) {
    for (TagBuilder<?> builder : builders) {
      this.buildJson(builder.getFullPath(), builder::json);
    }
  }
}
