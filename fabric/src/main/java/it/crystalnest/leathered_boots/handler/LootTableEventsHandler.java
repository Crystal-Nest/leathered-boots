package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.item.ItemRegistry;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.loot.BiomesCheck;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link LootTableEvents} handler.
 */
public final class LootTableEventsHandler {
  private LootTableEventsHandler() {}

  /**
   * Handles modifying Vanilla loot tables to include mod items.
   *
   * @param key loot table key.
   * @param builder builder of the loot table being loaded.
   * @param source loot table source.
   * @param provider holder lookup provider.
   */
  public static void handle(ResourceKey<LootTable> key, LootTable.Builder builder, LootTableSource source, HolderLookup.Provider provider) {
    switch (key.location().toString()) {
      case "minecraft:chests/village/village_snowy_house" -> {
        builder.pool(buildPool(0.2F, ItemRegistry.LEATHERED_CHAIN_BOOTS));
        builder.pool(buildPool(0.1F, ItemRegistry.LEATHERED_IRON_BOOTS));
        builder.pool(buildPool(0.05F, ItemRegistry.LEATHERED_DIAMOND_BOOTS));
      }
      case "minecraft:chests/igloo_chest" -> {
        builder.pool(buildPool(0.2F, ItemRegistry.LEATHERED_GOLDEN_BOOTS));
        builder.pool(buildPool(0.1F, ItemRegistry.LEATHERED_IRON_BOOTS));
        builder.pool(buildPool(0.05F, ItemRegistry.LEATHERED_DIAMOND_BOOTS));
        builder.pool(buildPool(0.5F, ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get()));
      }
      case "minecraft:chests/shipwreck_map", "minecraft:chests/shipwreck_treasure", "minecraft:chests/shipwreck_supply" -> builder.pool(buildPool(0.2F, Biomes.SNOWY_BEACH));
      case "minecraft:chests/pillager_outpost" -> builder.pool(buildPool(0.334F, Biomes.GROVE, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_PLAINS));
    }
  }

  /**
   * Handles replacing Vanilla loot tables to include mod items.
   *
   * @param key loot table key.
   * @param original loot table being modified.
   * @param source loot table source.
   * @param provider holder lookup provider.
   * @return modified or original loot table.
   */
  public static LootTable handle(ResourceKey<LootTable> key, LootTable original, LootTableSource source, HolderLookup.Provider provider) {
    if ("minecraft:archaeology/trail_ruins_rare".equals(key.location().toString())) {
      return LootTable
        .lootTable()
        .pool(LootPool.lootPool().with(original.pools.getFirst().entries).with(LootItem.lootTableItem(ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get()).when(BiomesCheck.builder(Biomes.SNOWY_TAIGA)).build()).build())
        .build();
    }
    return null;
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the given {@link Item}.
   *
   * @param chance chance to spawn the item.
   * @param item item to spawn.
   * @return loot pool.
   */
  private static LootPool buildPool(float chance, Item item) {
    return LootPool.lootPool()
      .setRolls(ConstantValue.exactly(1))
      .conditionally(LootItemRandomChanceCondition.randomChance(chance).build())
      .with(LootItem.lootTableItem(item).build())
      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
      .build();
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the given {@link LeatheredBootsItem}.
   *
   * @param chance chance to spawn the item.
   * @param item leathered boots item.
   * @return loot pool.
   */
  private static LootPool buildPool(float chance, Supplier<? extends Item> item) {
    return buildPool(chance, item.get());
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the {@link ItemRegistry#LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM}, possible only in the given {@code biomes}.
   *
   * @param chance chance to spawn the item.
   * @param biomes biomes where the item can spawn.
   * @return loot pool.
   */
  @SafeVarargs
  private static LootPool buildPool(float chance, ResourceKey<Biome>... biomes) {
    return LootPool.lootPool()
      .setRolls(ConstantValue.exactly(1))
      .conditionally(LootItemRandomChanceCondition.randomChance(chance).build())
      .conditionally(BiomesCheck.builder(List.of(biomes)).build())
      .with(LootItem.lootTableItem(ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get()).build())
      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
      .build();
  }
}
