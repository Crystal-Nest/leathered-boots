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
public class ArchaeologyLootModifier extends LootModifier {
  /**
   * {@link Supplier} for this {@link LootModifier} {@link Codec}.
   */
  public static final Supplier<Codec<ArchaeologyLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(instance -> codecStart(instance)
    .and(Addition.CODEC.listOf().fieldOf("additions").forGetter(modifier -> modifier.additions))
    .and(Codec.FLOAT.fieldOf("chance_to_replace").forGetter(modifier -> modifier.chanceToReplace))
  .apply(instance, ArchaeologyLootModifier::new)));

  /**
   * Additional items to add to the chest loot.
   */
  private final List<Addition> additions;
  /**
   * Chance to replace the current archaeology loot.
   */
  private final Float chanceToReplace;

  /**
   * @param conditionsIn {@link LootModifier#conditions}.
   * @param additions {@link #additions}.
   * @param chanceToReplace {@link #chanceToReplace}.
   */
  public ArchaeologyLootModifier(LootItemCondition[] conditionsIn, List<Addition> additions, Float chanceToReplace) {
    super(conditionsIn);
    this.additions = additions;
    this.chanceToReplace = chanceToReplace;
  }

  @Nonnull
  @Override
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (generatedLoot == null) {
      generatedLoot = new ObjectArrayList<>();
    }
    List<Item> items = additions.stream().filter(addition -> BiomesCheck.builder(addition.biomes).build().test(context)).map(addition -> addition.item).toList();
    if (!items.isEmpty() && context.getRandom().nextFloat() <= chanceToReplace) {
      generatedLoot.clear();
      generatedLoot.add(new ItemStack(items.get(context.getRandom().nextInt(0, items.size()))));
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
      ResourceKey.codec(Registries.BIOME).listOf().optionalFieldOf("biomes").forGetter(addition -> Optional.of(addition.biomes))
    ).apply(instance, Addition::new));

    /**
     * {@link Item} to add to the loot.
     */
    private final Item item;
    /**
     * Allowed biomes for the {@link #item} to add to the loot.
     * <p>
     * When empty or not specified, it allows all biomes.
     */
    private final List<ResourceKey<Biome>> biomes;

    /**
     * @param item {@link #item}.
     * @param biomes {@link #biomes}.
     */
    private Addition(Item item, Optional<List<ResourceKey<Biome>>> biomes) {
      this.item = item;
      this.biomes = biomes.orElse(List.of());
    }
  }
}
