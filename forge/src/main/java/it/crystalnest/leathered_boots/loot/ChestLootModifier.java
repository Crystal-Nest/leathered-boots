package it.crystalnest.leathered_boots.loot;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Chests loot modifier.
 */
public final class ChestLootModifier extends LootModifier {
  /**
   * Additional items to add to the chest loot.
   */
  private final HashMap<ItemStack, Float> additions;

  /**
   * @param conditionsIn {@link LootModifier#conditions}.
   * @param additions {@link #additions}.
   */
  ChestLootModifier(LootItemCondition[] conditionsIn, HashMap<ItemStack, Float> additions) {
    super(conditionsIn);
    this.additions = additions;
  }

  @NotNull
  @Override
  protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
    for (Map.Entry<ItemStack, Float> entry : additions.entrySet()) {
      if (context.getRandom().nextFloat() <= entry.getValue()) {
        generatedLoot.add(entry.getKey());
      }
    }
    return generatedLoot;
  }

  /**
   * {@link ChestLootModifier} Serializer.
   */
  public static class Serializer extends GlobalLootModifierSerializer<ChestLootModifier> {
    @Override
    public ChestLootModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditionsIn) {
      HashMap<ItemStack, Float> additions = new HashMap<>();
      for (JsonElement jsonElement : GsonHelper.getAsJsonArray(json, "additions")) {
        JsonObject entry = jsonElement.getAsJsonObject();
        additions.put(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(entry, "item"))), GsonHelper.getAsInt(entry, "quantity")), GsonHelper.getAsFloat(entry, "chance"));
      }
      return new ChestLootModifier(conditionsIn, additions);
    }

    @Override
    public JsonObject write(ChestLootModifier instance) {
      return makeConditions(instance.conditions);
    }
  }
}
