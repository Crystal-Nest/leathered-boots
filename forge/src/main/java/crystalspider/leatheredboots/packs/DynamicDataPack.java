package crystalspider.leatheredboots.packs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
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
import net.minecraft.server.packs.metadata.pack.PackMetadataSectionSerializer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries.Keys;

public class DynamicDataPack implements PackResources {
  protected static final Logger LOGGER = LogManager.getLogger();

  public final ResourceLocation name;
  public final String namespace;
  public final Set<String> namespaces = new HashSet<>();
  public final Supplier<PackMetadataSection> metadata;
  public final Map<ResourceLocation, byte[]> resources = new ConcurrentHashMap<>();

  public DynamicDataPack(ResourceLocation name) {
    this.name = name;
    this.namespace = name.getNamespace();
    this.namespaces.add(name.getNamespace());
    this.metadata = Suppliers.memoize(()-> new PackMetadataSection(Component.translatable(namespace + "_dynamic_" + name.getPath()), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA.bridgeType)));
    FMLJavaModLoadingContext.get().getModEventBus().addListener((AddPackFindersEvent event) -> {
      if (event.getPackType() == PackType.SERVER_DATA) {
        event.addRepositorySource(packConsumer -> packConsumer.accept(
          Pack.create(
            this.packId(),
            Component.translatable(name.toString()),
            true,
            str -> this,
            new Pack.Info(metadata.get().getDescription(), metadata.get().getPackFormat(PackType.SERVER_DATA), FeatureFlagSet.of()),
            PackType.SERVER_DATA,
            Pack.Position.TOP,
            false,
            PackSource.BUILT_IN
          )
        ));
      }
    });
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
    return false; // true;
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
  public IoSupplier<InputStream> getRootResource(String... strings) {
    return null;
  }

  @Override
  public void listResources(PackType packType, String namespace, String id, ResourceOutput output) {
    if (packType == PackType.SERVER_DATA && this.namespaces.contains(namespace)) {
      this.resources.entrySet().stream()
        .filter(r -> (r.getKey().getNamespace().equals(namespace) && r.getKey().getPath().startsWith(id)))
        .forEach(r -> output.accept(r.getKey(), () -> new ByteArrayInputStream(r.getValue())));
    }
  }

  @Override
  public IoSupplier<InputStream> getResource(PackType type, ResourceLocation id) {
    byte[] res = this.resources.get(id);
    if (res != null) {
      return () -> {
        if (type == PackType.SERVER_DATA) {
          return new ByteArrayInputStream(res);
        }
        throw new IOException(String.format("Tried to access wrong type of resource on %s.", this.name));
      };
    }
    return null;
  }

  @Override
  public void close() {}

  private void build(ResourceLocation path, byte[] bytes) {
    this.namespaces.add(path.getNamespace());
    this.resources.put(path, bytes);
  }

  private void build(ResourceLocation path, JsonElement json) {
    try {
      StringWriter stringWriter = new StringWriter();
      JsonWriter jsonWriter = new JsonWriter(stringWriter);
      jsonWriter.setIndent("  ");
      Streams.write(json, jsonWriter);
      jsonWriter.close();
      this.build(ResourceType.GENERIC.getPath(path), stringWriter.toString().getBytes());
    } catch (IOException e) {
      LOGGER.error("Failed to write JSON " + path + " to resource pack " + name + ".", e);
    }
  }

  public void buildItemTag(TagBuilder builder) {
    ResourceLocation tagLocation = builder.getLocation();
    ResourceLocation dataLocation = ResourceType.TAGS.getPath(new ResourceLocation(tagLocation.getNamespace(), Keys.ITEMS.location().getPath() + "s/" + tagLocation.getPath()));
    if (this.resources.containsKey(dataLocation)) {
      try {
        InputStreamReader jsonStream = new InputStreamReader(new ByteArrayInputStream(this.resources.get(dataLocation)), StandardCharsets.UTF_8);
        builder.addJson(GsonHelper.parse(jsonStream));
        jsonStream.close();
      } catch (Exception e) {
        LOGGER.error("The following error was thrown while attempting to read existing datapack resources from " + dataLocation, e);
      }
    }
    this.build(dataLocation, builder.json());
  }

  // TODO: Check compatibility with ModernFix.
}
