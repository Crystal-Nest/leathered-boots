package crystalspider.leatheredboots.handler;

import java.util.List;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import crystalspider.leatheredboots.loot.BiomesCheckLootCondition;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.item.Item;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootPool.Builder;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

/**
 * {@link LootTableEvents} handler.
 */
public class LootTableEventsHandler {
  /**
   * {@link Identifier} of snowy villager house chest loot table.
   */
  private static final Identifier VILLAGE_SNOWY_HOUSE_IDENTIFIER = new Identifier("minecraft", "chests/village/village_snowy_house");
  /**
   * {@link Identifier} of igloo chest loot table.
   */
  private static final Identifier IGLOO_CHEST_IDENTIFIER = new Identifier("minecraft", "chests/igloo_chest");
  /**
   * {@link Identifier} of shipwreck map chest loot table.
   */
  private static final Identifier SHIPWRECK_MAP_IDENTIFIER = new Identifier("minecraft", "chests/shipwreck_map");
  /**
   * {@link Identifier} of shipwreck treasure chest loot table.
   */
  private static final Identifier SHIPWRECK_TREASURE_IDENTIFIER = new Identifier("minecraft", "chests/shipwreck_treasure");
  /**
   * {@link Identifier} of shipwreck supply chest loot table.
   */
  private static final Identifier SHIPWRECK_SUPPLY_IDENTIFIER = new Identifier("minecraft", "chests/shipwreck_supply");
  /**
   * {@link Identifier} of pillager outpost chest loot table.
   */
  private static final Identifier PILLAGER_OUTPOST_IDENTIFIER = new Identifier("minecraft", "chests/pillager_outpost");
  /**
   * {@link Identifier} of trail ruins rare archaeology drops loot table.
   */
  private static final Identifier TRAIL_RUINS_RARE_IDENTIFIER = new Identifier("minecraft", "archaeology/trail_ruins_rare");

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
    if (identifier.equals(VILLAGE_SNOWY_HOUSE_IDENTIFIER)) {
      builder.pool(buildPool(0.2f, LeatheredArmorMaterial.LEATHERED_CHAIN));
      builder.pool(buildPool(0.1f, LeatheredArmorMaterial.LEATHERED_IRON));
      builder.pool(buildPool(0.05f, LeatheredArmorMaterial.LEATHERED_DIAMOND));
    } else if (identifier.equals(IGLOO_CHEST_IDENTIFIER)) {
      builder.pool(buildPool(0.2f, LeatheredArmorMaterial.LEATHERED_GOLD));
      builder.pool(buildPool(0.1f, LeatheredArmorMaterial.LEATHERED_IRON));
      builder.pool(buildPool(0.05f, LeatheredArmorMaterial.LEATHERED_DIAMOND));
      builder.pool(buildPool(0.5f, ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM));
    } else if (identifier.equals(SHIPWRECK_MAP_IDENTIFIER) || identifier.equals(SHIPWRECK_TREASURE_IDENTIFIER) || identifier.equals(SHIPWRECK_SUPPLY_IDENTIFIER)) {
      builder.pool(buildBiomeConditionalPool(0.2f, BiomeKeys.SNOWY_BEACH));
    } else if (identifier.equals(PILLAGER_OUTPOST_IDENTIFIER)) {
      builder.pool(buildBiomeConditionalPool(0.334f, List.of(BiomeKeys.GROVE, BiomeKeys.SNOWY_SLOPES, BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS)));
    } else if (identifier.equals(TRAIL_RUINS_RARE_IDENTIFIER)) {
      builder.pool(buildBiomeConditionalPool(0.1f, BiomeKeys.SNOWY_TAIGA));
    }
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the given {@link Item}.
   * 
   * @param chance
   * @param item
   * @return loot pool.
   */
  private static Builder buildPool(float chance, Item item) {
    return LootPool.builder()
      .rolls(ConstantLootNumberProvider.create(1))
      .conditionally(RandomChanceLootCondition.builder(chance))
      .with(ItemEntry.builder(item))
      .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)));
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find a {@link LeatheredBootsItem} of the given {@link LeatheredArmorMaterial}.
   * 
   * @param chance
   * @param armorMaterial
   * @return loot pool.
   */
  private static Builder buildPool(float chance, LeatheredArmorMaterial armorMaterial) {
    return buildPool(chance, LeatheredBoots.getLeatheredBoots(armorMaterial));
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the given {@link Item}, possible only in the given {@code biomes}.
   * 
   * @param chance
   * @param biomes
   * @return loot pool.
   */
  private static Builder buildBiomeConditionalPool(float chance, List<RegistryKey<Biome>> biomes) {
    return LootPool.builder()
      .rolls(ConstantLootNumberProvider.create(1))
      .conditionally(RandomChanceLootCondition.builder(chance))
      .conditionally(BiomesCheckLootCondition.builder(biomes))
      .with(ItemEntry.builder(ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM))
      .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)));
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find the given {@link Item}, possible only in the given {@code biome}.
   * 
   * @param chance
   * @param biome
   * @return loot pool.
   */
  private static Builder buildBiomeConditionalPool(float chance, RegistryKey<Biome> biome) {
    return buildBiomeConditionalPool(chance, List.of(biome));
  }
}
