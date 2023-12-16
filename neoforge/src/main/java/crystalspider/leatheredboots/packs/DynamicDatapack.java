package crystalspider.leatheredboots.packs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.flag.FeatureFlagSet;

/**
 * Dynamic Datapack.
 */
public class DynamicDatapack implements PackResources {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();

  /**
   * Datapack name.
   */
  private final ResourceLocation name;
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
  private final Supplier<PackMetadataSection> metadata;
  /**
   * Datapack resources.
   */
  private final Map<ResourceLocation, Supplier<byte[]>> resources = new ConcurrentHashMap<>();

  /**
   * @param name {@link #name}.
   * @param builders list of {@link TagBuilder}s.
   */
  public DynamicDatapack(ResourceLocation name, TagBuilder<?> ...builders) {
    this.name = name;
    this.namespace = name.getNamespace();
    this.namespaces.add(name.getNamespace());
    this.metadata = Suppliers.memoize(()-> new PackMetadataSection(Component.translatable(namespace + "_dynamic_" + name.getPath()), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA)));
    this.build(builders);
  }

  /**
   * Creates a {@link Pack} instance of this dynamic datapack.
   * 
   * @return {@link Pack} instance.
   */
  public Pack create() {
    return Pack.create(
      packId(),
      Component.translatable(packId()),
      true,
      new DynamicResourcesSupplier(this),
      new Pack.Info(metadata.get().description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), true),
      Pack.Position.TOP,
      false,
      PackSource.BUILT_IN
    );
  }

  @Override
  public String packId() {
    return name.toString();
  }

  @Override
  public String toString() {
    return packId();
  }

  @Override
  public boolean isHidden() {
    return true;
  }

  @Override
  public Set<String> getNamespaces(PackType packType) {
    return this.namespaces;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T getMetadataSection(MetadataSectionSerializer<T> serializer) {
    return serializer.getMetadataSectionName().equals(PackMetadataSection.TYPE.getMetadataSectionName()) ? (T) this.metadata : null;
  }

  @Override
  @Nullable
  public IoSupplier<InputStream> getRootResource(String ...strings) {
    return null;
  }

  @Override
  public void listResources(PackType packType, String namespace, String id, ResourceOutput output) {
    if (packType == PackType.SERVER_DATA && this.namespaces.contains(namespace)) {
      this.resources.entrySet().stream()
        .filter(resource -> (resource.getKey().getNamespace().equals(namespace) && resource.getKey().getPath().startsWith(id)))
        .forEach(resource -> output.accept(resource.getKey(), () -> new ByteArrayInputStream(resource.getValue().get())));
    }
  }

  @Override
  public IoSupplier<InputStream> getResource(PackType type, ResourceLocation id) {
    if (this.resources.containsKey(id)) {
      return () -> {
        if (type == PackType.SERVER_DATA) {
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
  private void buildBytes(ResourceLocation path, Supplier<byte[]> bytes) {
    this.namespaces.add(path.getNamespace());
    this.resources.put(path, Suppliers.memoize(bytes::get));
  }

  /**
   * Adds the given {@link JsonElement} {@link Supplier} to the ones to build this datapack resources.
   * 
   * @param path
   * @param json
   */
  private void buildJson(ResourceLocation path, Supplier<JsonElement> json) {
    this.buildBytes(DataResourceType.GENERIC.getPath(path), () -> {
      try (StringWriter stringWriter = new StringWriter(); JsonWriter jsonWriter = new JsonWriter(stringWriter);) {
        jsonWriter.setIndent("  ");
        Streams.write(json.get(), jsonWriter);
        jsonWriter.close();
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
