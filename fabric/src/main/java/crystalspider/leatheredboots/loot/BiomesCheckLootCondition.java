package crystalspider.leatheredboots.loot;

import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;

/**
 * {@link LootCondition} to check whether the location is in one of the provided biomes.
 */
public class BiomesCheckLootCondition implements LootCondition {
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

  @Override
  public boolean test(LootContext context) {
    Vec3d origin = context.get(LootContextParameters.ORIGIN);
    return origin != null && this.predicate.test(context.getWorld(), BlockPos.ofFloored(origin));
  }

  @Override
  public LootConditionType getType() {
    return LootRegistry.LOCATION_CHECK;
  }
  
  /**
   * {@link BiomesCheckLootCondition} {@link JsonSerializer}.
   */
  static class Serializer implements JsonSerializer<BiomesCheckLootCondition> {
    @Override
    public void toJson(JsonObject jsonObject, BiomesCheckLootCondition biomesCheckLootCondition, JsonSerializationContext jsonSerializationContext) {
      jsonObject.add("predicate", biomesCheckLootCondition.predicate.toJson());
    }

    @Override
    public BiomesCheckLootCondition fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
      return new BiomesCheckLootCondition(BiomesPredicate.fromJson(jsonObject.get("predicate")));
    }
  }
}
