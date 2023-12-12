package crystalspider.leatheredboots.loot;

import java.util.List;
import java.util.function.BiPredicate;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

/**
 * {@link BiPredicate} to check whether a {@link BlockPos} is in the provided list of biomes.
 */
public class BiomesPredicate implements BiPredicate<ServerWorld, BlockPos> {
  /**
   * Neutral {@link BiomesPredicate}.
   * <p>
   * Use this instead of a {@code new BiomesPredicate} with an empty biome list if you need it to always return {@code true}.
   */
  public static final BiomesPredicate ANY = new BiomesPredicate(List.of());

  /**
   * List of biomes to check against.
   */
  private final List<RegistryKey<Biome>> biomes;

  /**
   * @param biomes {@link #biomes}.
   */
  public BiomesPredicate(List<RegistryKey<Biome>> biomes) {
    this.biomes = biomes;
  }

  @Override
  public boolean test(ServerWorld world, BlockPos pos) {
    return this == ANY || world.canSetBlock(pos) && (this.biomes.size() == 0 || this.biomes.stream().anyMatch(biome -> world.getBiome(pos).matchesKey(biome)));
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
      for (RegistryKey<Biome> biome : this.biomes) {
        biomes.add(biome.getValue().toString());
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
  public static BiomesPredicate fromJson(@Nullable JsonElement json) {
    return json != null && !json.isJsonNull() ? new BiomesPredicate(JsonHelper.getArray(JsonHelper.asObject(json, "biomes"), "biomes", new JsonArray()).asList().stream().map(biome -> RegistryKey.of(RegistryKeys.BIOME, new Identifier(JsonHelper.asString(biome, "biome")))).toList()) : ANY;
  }
}