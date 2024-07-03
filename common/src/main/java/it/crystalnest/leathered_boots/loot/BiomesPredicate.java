package it.crystalnest.leathered_boots.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;

import java.util.List;
import java.util.function.BiPredicate;

import static com.mojang.serialization.codecs.RecordCodecBuilder.create;

/**
 * {@link BiPredicate} to check whether a {@link BlockPos} is in the provided list of biomes.
 */
public final class BiomesPredicate implements BiPredicate<ServerLevel, BlockPos> {
  /**
   * {@link Codec}.
   */
  public static final Codec<BiomesPredicate> CODEC = create(instance -> instance.group(ResourceKey.codec(Registries.BIOME).listOf().fieldOf("biomes").forGetter(predicate -> predicate.biomes)).apply(instance, BiomesPredicate::new));

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
    return this.biomes.isEmpty() || world.isLoaded(pos) && this.biomes.stream().anyMatch(biome -> world.getBiome(pos).is(biome));
  }
}
