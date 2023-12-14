package crystalspider.leatheredboots.loot;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

/**
 * {@link LootCondition} to check whether the location is in one of the provided biomes.
 */
public class BiomesCheckLootCondition implements LootCondition {
  /**
   * {@link Codec}.
   */
  public static final Codec<BiomesCheckLootCondition> CODEC = RecordCodecBuilder.create(instance -> instance.group(BiomesPredicate.CODEC.fieldOf("predicate").forGetter(check -> check.predicate)).apply(instance, BiomesCheckLootCondition::new));

  /**
   * {@link BiomesPredicate}.
   */
  private final BiomesPredicate predicate;

  /**
   * @param predicate {@link #predicate}.
   */
  BiomesCheckLootCondition(BiomesPredicate predicate) {
    this.predicate = predicate;
  }

  /**
   * {@link BiomesCheckLootCondition} builder.
   * 
   * @param biomes
   * @return a {@link LootCondition} builder for {@link BiomesCheckLootCondition}.
   */
  public static LootCondition.Builder builder(List<RegistryKey<Biome>> biomes) {
    return () -> new BiomesCheckLootCondition(new BiomesPredicate(biomes));
  }

  /**
   * {@link BiomesCheckLootCondition} builder.
   * 
   * @param biome
   * @return a {@link LootCondition} builder for {@link BiomesCheckLootCondition}.
   */
  public static LootCondition.Builder builder(RegistryKey<Biome> biome) {
    return () -> new BiomesCheckLootCondition(new BiomesPredicate(List.of(biome)));
  }

  @Override
  public boolean test(LootContext context) {
    Vec3d origin = context.get(LootContextParameters.ORIGIN);
    return origin != null && this.predicate.test(context.getWorld(), BlockPos.ofFloored(origin));
  }

  @Override
  public LootConditionType getType() {
    return LootRegistry.BIOMES_CHECK;
  }
}
