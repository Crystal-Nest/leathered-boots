package it.crystalnest.leathered_boots.loot;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.mojang.serialization.codecs.RecordCodecBuilder.create;

/**
 * {@link LootItemCondition} to check whether the location is in one of the provided biomes.
 */
public final class BiomesCheck implements LootItemCondition {
  /**
   * {@link Codec}.
   */
  public static final Codec<BiomesCheck> CODEC = create(instance -> instance.group(BiomesPredicate.CODEC.fieldOf("predicate").forGetter(check -> check.predicate)).apply(instance, BiomesCheck::new));

  /**
   * {@link BiomesPredicate}.
   */
  private final BiomesPredicate predicate;

  /**
   * @param predicate {@link #predicate}.
   */
  BiomesCheck(BiomesPredicate predicate) {
    this.predicate = predicate;
  }

  /**
   * {@link BiomesCheck} builder.
   *
   * @param biomes list of biomes for the check.
   * @return a {@link LootItemCondition} builder for {@link BiomesCheck}.
   */
  public static Builder builder(List<ResourceKey<Biome>> biomes) {
    return () -> new BiomesCheck(new BiomesPredicate(biomes));
  }

  /**
   * {@link BiomesCheck} builder.
   *
   * @param biome biome for the check.
   * @return a {@link LootItemCondition} builder for {@link BiomesCheck}.
   */
  public static Builder builder(ResourceKey<Biome> biome) {
    return () -> new BiomesCheck(new BiomesPredicate(List.of(biome)));
  }

  @Override
  public boolean test(LootContext context) {
    Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
    return origin != null && this.predicate.test(context.getLevel(), BlockPos.containing(origin));
  }

  @NotNull
  @Override
  public LootItemConditionType getType() {
    return CommonLootRegistry.BIOMES_CHECK.get();
  }
}
