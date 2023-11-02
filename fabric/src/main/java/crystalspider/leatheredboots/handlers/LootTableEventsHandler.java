package crystalspider.leatheredboots.handlers;

import crystalspider.leatheredboots.item.ItemRegistry;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

/**
 * {@link LootTableEvents} handler.
 */
public class LootTableEventsHandler {
  /**
   * {@link Identifier} of igloo chest loot table.
   */
  private static final Identifier IGLOO_CHEST_IDENTIFIER = new Identifier("minecraft", "chests/igloo_chest");
  /**
   * {@link Identifier} of snowy villager house loot table.
   */
  private static final Identifier VILLAGE_SNOWY_HOUSE_IDENTIFIER = new Identifier("minecraft", "chests/village/village_snowy_house");

  /**
   * Handles modifing Vanilla loot table to include leathered boots.
   * 
   * @param resourceManager
   * @param lootManager
   * @param identifier
   * @param builder
   * @param lootTableSource
   */
  public static void handle(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, LootTable.Builder builder, LootTableSource lootTableSource) {
    if (identifier.equals(IGLOO_CHEST_IDENTIFIER)) {
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.2F))
        .with(ItemEntry.builder(ItemRegistry.GOLDEN_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.1F))
        .with(ItemEntry.builder(ItemRegistry.IRON_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.05F))
        .with(ItemEntry.builder(ItemRegistry.DIAMOND_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
    } else if (identifier.equals(VILLAGE_SNOWY_HOUSE_IDENTIFIER)) {
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.2F))
        .with(ItemEntry.builder(ItemRegistry.CHAINMAIL_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.1F))
        .with(ItemEntry.builder(ItemRegistry.IRON_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
      builder.pool(
        LootPool.builder()
        .rolls(ConstantLootNumberProvider.create(1))
        .conditionally(RandomChanceLootCondition.builder(0.05F))
        .with(ItemEntry.builder(ItemRegistry.DIAMOND_LEATHERED_BOOTS))
        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)))
      );
    }
  }
}
