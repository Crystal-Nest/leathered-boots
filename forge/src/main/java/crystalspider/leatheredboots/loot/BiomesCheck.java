package crystalspider.leatheredboots.loot;

import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.phys.Vec3;

/**
 * {@link LootItemCondition} to check whether the location is in one of the provided biomes.
 */
public class BiomesCheck implements LootItemCondition {
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
   * @param biomes
   * @return a {@link LootCondition} builder for {@link BiomesCheck}.
   */
  public static LootItemCondition.Builder builder(List<ResourceKey<Biome>> biomes) {
    return () -> new BiomesCheck(new BiomesPredicate(biomes));
  }

  /**
   * {@link BiomesCheck} builder.
   * 
   * @param biome
   * @return a {@link LootCondition} builder for {@link BiomesCheck}.
   */
  public static LootItemCondition.Builder builder(ResourceKey<Biome> biome) {
    return () -> new BiomesCheck(new BiomesPredicate(List.of(biome)));
  }

  @Override
  public boolean test(LootContext context) {
    Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
    return origin != null && this.predicate.test(context.getLevel(), BlockPos.containing(origin));
  }

  @Override
  public LootItemConditionType getType() {
    return LootRegistry.BIOMES_CHECK.get();
  }
  
  /**
   * {@link BiomesCheck} {@link net.minecraft.world.level.storage.loot.Serializer}.
   */
  static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<BiomesCheck> {
    @Override
    public void serialize(JsonObject jsonObject, BiomesCheck BiomesCheck, JsonSerializationContext jsonSerializationContext) {
      jsonObject.add("predicate", BiomesCheck.predicate.toJson());
    }

    @Override
    public BiomesCheck deserialize(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
      return new BiomesCheck(BiomesPredicate.fromJson(jsonObject.get("predicate")));
    }
  }
}
