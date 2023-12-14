package crystalspider.leatheredboots.loot;

import java.util.List;
import java.util.function.BiPredicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

/**
 * {@link BiPredicate} to check whether a {@link BlockPos} is in the provided list of biomes.
 */
public class BiomesPredicate implements BiPredicate<ServerWorld, BlockPos> {
  /**
   * {@link Codec}.
   */
  public static final Codec<BiomesPredicate> CODEC = RecordCodecBuilder.create(instance -> instance.group(RegistryKey.createCodec(RegistryKeys.BIOME).listOf().fieldOf("biomes").forGetter(predicate -> predicate.biomes)).apply(instance, BiomesPredicate::new));
  
  /**
   * Neutral {@link BiomesPredicate}.
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
    return this.biomes.size() == 0 || world.canSetBlock(pos) && this.biomes.stream().anyMatch(biome -> world.getBiome(pos).matchesKey(biome));
  }
}
