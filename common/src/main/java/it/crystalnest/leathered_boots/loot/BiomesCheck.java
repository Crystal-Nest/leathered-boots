package it.crystalnest.leathered_boots.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

/**
 * {@link LootItemCondition} to check whether the location is in one of the provided biomes.
 *
 * @param predicate {@link BiomesPredicate}.
 */
public record BiomesCheck(BiomesPredicate predicate) implements LootItemCondition {
  /**
   * {@link MapCodec}.
   */
  public static final MapCodec<BiomesCheck> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(BiomesPredicate.CODEC.fieldOf("predicate").forGetter(BiomesCheck::predicate)).apply(instance, BiomesCheck::new));

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
    return context.getOptionalParameter(LootContextParams.ORIGIN) instanceof Vec3 origin && this.predicate.test(context.getLevel(), BlockPos.containing(origin));
  }

  @NotNull
  @Override
  public LootItemConditionType getType() {
    return CommonLootRegistry.BIOMES_CHECK.get();
  }
}
