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
   * Handles modifing Vanilla loot tables to include mod items.
   * 
   * @param resourceManager
   * @param lootManager
   * @param identifier
   * @param builder
   * @param lootTableSource
   */
  public static void handle(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, LootTable.Builder builder, LootTableSource source) {
    switch (identifier.toString()) {
      case "minecraft:chests/village/village_snowy_house":
        builder.pool(buildPool(0.2f, LeatheredArmorMaterial.LEATHERED_CHAIN));
        builder.pool(buildPool(0.1f, LeatheredArmorMaterial.LEATHERED_IRON));
        builder.pool(buildPool(0.05f, LeatheredArmorMaterial.LEATHERED_DIAMOND));
        break;
      case "minecraft:chests/igloo_chest":
        builder.pool(buildPool(0.2f, LeatheredArmorMaterial.LEATHERED_GOLD));
        builder.pool(buildPool(0.1f, LeatheredArmorMaterial.LEATHERED_IRON));
        builder.pool(buildPool(0.05f, LeatheredArmorMaterial.LEATHERED_DIAMOND));
        builder.pool(buildPool(0.5f, ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM));
        break;
      case "minecraft:chests/shipwreck_map":
      case "minecraft:chests/shipwreck_treasure":
      case "minecraft:chests/shipwreck_supply":
        builder.pool(buildBiomeConditionalPool(0.2f, BiomeKeys.SNOWY_BEACH));
        break;
      case "minecraft:chests/pillager_outpost":
        builder.pool(buildBiomeConditionalPool(0.334f, List.of(BiomeKeys.GROVE, BiomeKeys.SNOWY_SLOPES, BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS)));
        break;
    }
  }

  /**
   * Handles replacing Vanilla loot tables to include mod items.
   * 
   * @param resourceManager
   * @param lootManager
   * @param identifier
   * @param original
   * @param source
   * @return replaced {@link LootTable} or {@code null}.
   */
  public static LootTable handle(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, LootTable original, LootTableSource source) {
    switch (identifier.toString()) {
      case "minecraft:archaeology/trail_ruins_rare":
        return LootTable.builder().pool(LootPool.builder().with(original.pools.get(0).entries).with(ItemEntry.builder(ItemRegistry.LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM).conditionally(BiomesCheckLootCondition.builder(BiomeKeys.SNOWY_TAIGA)).build())).build();
      default:
        return null;
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
