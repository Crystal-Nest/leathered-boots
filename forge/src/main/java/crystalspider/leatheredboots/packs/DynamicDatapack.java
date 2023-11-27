package crystalspider.leatheredboots.packs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
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
import net.minecraft.server.packs.metadata.pack.PackMetadataSectionSerializer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

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
   * @param builder {@link TagBuilder}.
   */
  public DynamicDatapack(ResourceLocation name, TagBuilder<?> builder) {
    this.name = name;
    this.namespace = name.getNamespace();
    this.namespaces.add(name.getNamespace());
    this.metadata = Suppliers.memoize(()-> new PackMetadataSection(Component.translatable(namespace + "_dynamic_" + name.getPath()), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA.bridgeType)));
    this.build(builder);
  }

  /**
   * Creates a {@link Pack} instance of this dynamic datapack.
   * 
   * @return {@link Pack} instance.
   */
  public Pack create() {
    return new Pack(
      getName(),
      Component.translatable(getName()),
      true,
      () -> this,
      new PackMetadataSection(metadata.get().getDescription(), metadata.get().getPackFormat(PackType.SERVER_DATA)),
      PackType.SERVER_DATA,
      Pack.Position.TOP,
      PackSource.BUILT_IN,
      true
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
    return serializer instanceof PackMetadataSectionSerializer ? (T) this.metadata : null;
  }

  @Override
  @Nullable
  public InputStream getRootResource(String fileName) {
    return null;
  }

  @Override
  public Collection<ResourceLocation> getResources(PackType packType, String namespace, String id, Predicate<ResourceLocation> allowedPathPredicate) {
    if (packType == PackType.SERVER_DATA && this.namespaces.contains(namespace)) {
      return this.resources.keySet().stream().filter(resource -> resource.getNamespace().equals(namespace) && resource.getPath().startsWith(id) && allowedPathPredicate.test(resource)).toList();
    }
    return Collections.emptyList();
  }

  @Override
  public InputStream getResource(PackType type, ResourceLocation id) {
    if (this.resources.containsKey(id) && type == PackType.SERVER_DATA) {
      return new ByteArrayInputStream(this.resources.get(id).get());
    }
    return null;
  }

  @Override
  public boolean hasResource(PackType type, ResourceLocation id) {
    return type == PackType.SERVER_DATA && this.resources.containsKey(id);
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
   * Uses the given {@link TagBuilder} to get a {@link Supplier} for this datapack resources.
   * 
   * @param builder
   */
  private void build(TagBuilder<?> builder) {
    this.buildJson(builder.getFullPath(), builder::json);
  }
}
