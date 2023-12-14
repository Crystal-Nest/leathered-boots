package crystalspider.leatheredboots.loot;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

/**
 * Chests loot modifier.
 */
public class ChestLootModifier extends LootModifier {
  /**
   * {@link Supplier} for this {@link LootModifier} {@link Codec}.
   */
  public static final Supplier<Codec<ChestLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance).and(Addition.CODEC.listOf().fieldOf("additions").forGetter(modifier -> modifier.additions)).apply(instance, ChestLootModifier::new)));

  /**
   * Additional items to add to the chest loot.
   */
  private final List<Addition> additions;

  /**
   * @param conditionsIn {@link LootModifier#conditions}.
   * @param additions {@link #additions}.
   */
  public ChestLootModifier(LootItemCondition[] conditionsIn, List<Addition> additions) {
    super(conditionsIn);
    this.additions = additions;
  }

  @Nonnull
  @Override
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (generatedLoot == null) {
      generatedLoot = new ObjectArrayList<>();
    }
    for (Addition addition : additions) {
      if (BiomesCheck.builder(addition.biomes).build().test(context) && context.getRandom().nextFloat() <= addition.chance) {
        generatedLoot.add(new ItemStack(addition.item, addition.quantity));
      }
    }
    return generatedLoot;
  }

  @Override
  public Codec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }

  /**
   * A single item addition to the loot.
   */
  private static class Addition {
    /**
     * {@link Codec}.
     */
    public static final Codec<Addition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
      BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(addition -> addition.item),
      Codec.FLOAT.fieldOf("chance").forGetter(addition -> addition.chance),
      Codec.INT.fieldOf("quantity").forGetter(addition -> addition.quantity),
      ResourceKey.codec(Registries.BIOME).listOf().optionalFieldOf("biomes").forGetter(addition -> Optional.of(addition.biomes))
    ).apply(instance, Addition::new));

    /**
     * {@link Item} to add to the loot.
     */
    private final Item item;
    /**
     * Chance for this {@link #item} to add to the loot.
     */
    private final Float chance;
    /**
     * Amount of this {@link #item} to add to the loot.
     */
    private final Integer quantity;
    /**
     * Allowed biomes for the {@link #item} to add to the loot.
     * <p>
     * When empty or not specified, it allows all biomes.
     */
    private final List<ResourceKey<Biome>> biomes;

    /**
     * @param item {@link #item}.
     * @param chance {@link #chance}.
     * @param quantity {@link #quantity}.
     * @param biomes {@link #biomes}.
     */
    private Addition(Item item, Float chance, Integer quantity, Optional<List<ResourceKey<Biome>>> biomes) {
      this.item = item;
      this.chance = chance;
      this.quantity = quantity;
      this.biomes = biomes.orElse(List.of());
    }
  }
}
