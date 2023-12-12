package crystalspider.leatheredboots.loot;

import java.util.List;
import java.util.function.BiPredicate;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;

/**
 * {@link BiPredicate} to check whether a {@link BlockPos} is in the provided list of biomes.
 */
public class BiomesPredicate implements BiPredicate<ServerLevel, BlockPos> {
  /**
   * Neutral {@link BiomesPredicate}.
   */
  public static final BiomesPredicate ANY = new BiomesPredicate(List.of());

  /**
   * List of biomes to check against.
   */
  private final List<ResourceKey<Biome>> biomes;

  /**
   * @param biomes {@link #biomes}.
   */
  public BiomesPredicate(List<ResourceKey<Biome>> biomes) {
    this.biomes = biomes;
  }

  @Override
  public boolean test(ServerLevel world, BlockPos pos) {
    return this.biomes.size() == 0 || world.isLoaded(pos) && this.biomes.stream().anyMatch(biome -> world.getBiome(pos).is(biome));
  }

  /**
   * Builds a {@link JsonElement} from {@code this} {@link BiomesPredicate}.
   * 
   * @return a {@link JsonElement} with data representing {@code this} {@link BiomesPredicate}.
   */
  public JsonElement toJson() {
    if (this != ANY) {
      JsonObject json = new JsonObject();
      JsonArray biomes = new JsonArray();
      for (ResourceKey<Biome> biome : this.biomes) {
        biomes.add(biome.location().toString());
      }
      json.add("biomes", biomes);
      return json;
    }
    return JsonNull.INSTANCE;
  }

  /**
   * Builds a {@link BiomesPredicate} from the given {@link JsonElement}.
   * 
   * @param json
   * @return a {@link BiomesPredicate} from JSON specifications.
   */
  @SuppressWarnings("null")
  public static BiomesPredicate fromJson(@Nullable JsonElement json) {
    return json != null && !json.isJsonNull() ? new BiomesPredicate(GsonHelper.getAsJsonArray(GsonHelper.convertToJsonObject(json, "biomes"), "biomes", new JsonArray()).asList().stream().map(biome -> ResourceKey.create(Registries.BIOME, new ResourceLocation(GsonHelper.convertToString(biome, "biome")))).toList()) : ANY;
  }
}