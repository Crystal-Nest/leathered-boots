package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.ItemRegistry;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.loot.BiomesCheck;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

/**
 * {@link LootTableEvents} handler.
 */
public final class LootTableEventsHandler {
  private LootTableEventsHandler() {}

  /**
   * Handles modifying Vanilla loot tables to include mod items.
   *
   * @param resourceManager resource manager.
   * @param lootManager loot manager.
   * @param id loot table ID.
   * @param builder loot table builder.
   * @param source loot table source.
   */
  public static void handle(ResourceManager resourceManager, LootDataManager lootManager, ResourceLocation id, LootTable.Builder builder, LootTableSource source) {
    switch (id.toString()) {
      case "minecraft:chests/village/village_snowy_house" -> {
        builder.pool(buildPool(0.2F, LeatheredArmorMaterial.LEATHERED_CHAIN));
        builder.pool(buildPool(0.1F, LeatheredArmorMaterial.LEATHERED_IRON));
        builder.pool(buildPool(0.05F, LeatheredArmorMaterial.LEATHERED_DIAMOND));
      }
      case "minecraft:chests/igloo_chest" -> {
        builder.pool(buildPool(0.2F, LeatheredArmorMaterial.LEATHERED_GOLD));
        builder.pool(buildPool(0.1F, LeatheredArmorMaterial.LEATHERED_IRON));
        builder.pool(buildPool(0.05F, LeatheredArmorMaterial.LEATHERED_DIAMOND));
        builder.pool(buildPool(0.5F, ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get()));
      }
      case "minecraft:chests/shipwreck_map", "minecraft:chests/shipwreck_treasure", "minecraft:chests/shipwreck_supply" -> builder.pool(buildPool(0.2F, Biomes.SNOWY_BEACH));
      case "minecraft:chests/pillager_outpost" -> builder.pool(buildPool(0.334F, Biomes.GROVE, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_PLAINS));
    }
  }

  /**
   * Handles replacing Vanilla loot tables to include mod items.
   *
   * @param resourceManager resource manager.
   * @param lootManager loot manager.
   * @param id loot table ID.
   * @param original original loot table.
   * @param source loot table source.
   * @return replaced {@link LootTable} or {@code null}.
   */
  public static LootTable handle(ResourceManager resourceManager, LootDataManager lootManager, ResourceLocation id, LootTable original, LootTableSource source) {
    if ("minecraft:archaeology/trail_ruins_rare".equals(id.toString())) {
      return LootTable.lootTable().pool(LootPool.lootPool().with(original.pools.get(0).entries).with(LootItem.lootTableItem(ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get()).when(BiomesCheck.builder(Biomes.SNOWY_TAIGA)).build()).build()).build();
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
   * Builds a loot pool with the given random {@code chance} to find a {@link LeatheredBootsItem} of the given {@link LeatheredArmorMaterial}.
   *
   * @param chance chance to spawn the item.
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @return loot pool.
   */
  private static LootPool buildPool(float chance, LeatheredArmorMaterial armorMaterial) {
    return buildPool(chance, LeatheredBootsManager.getBoots(Constants.MOD_ID, armorMaterial));
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
