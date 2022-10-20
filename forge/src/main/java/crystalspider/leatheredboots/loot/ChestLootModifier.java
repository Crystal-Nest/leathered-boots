package crystalspider.leatheredboots.loot;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

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

  public ChestLootModifier(LootItemCondition[] conditionsIn, List<Addition> additions) {
    super(conditionsIn);
    this.additions = additions;
  }

  @Override
  @Nonnull
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (generatedLoot == null) {
      generatedLoot = new ObjectArrayList<>();
    }
    for (Addition addition : additions) {
      if (context.getRandom().nextFloat() <= addition.chance) {
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
      ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(addition -> addition.item),
      Codec.FLOAT.fieldOf("chance").forGetter(addition -> addition.chance),
      Codec.INT.fieldOf("quantity").forGetter(addition -> addition.quantity)
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
     * Amount of this {@link #item} to add to the loot
     */
    private final Integer quantity;

    private Addition(Item item, Float chance, Integer quantity) {
      this.item = item;
      this.chance = chance;
      this.quantity = quantity;
    }
  }
}
