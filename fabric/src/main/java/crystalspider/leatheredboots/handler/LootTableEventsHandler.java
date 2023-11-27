package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootPool.Builder;
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
      builder.pool(buildPool(0.2F, LeatheredArmorMaterial.LEATHERED_GOLD));
      builder.pool(buildPool(0.1F, LeatheredArmorMaterial.LEATHERED_IRON));
      builder.pool(buildPool(0.05F, LeatheredArmorMaterial.LEATHERED_DIAMOND));
    } else if (identifier.equals(VILLAGE_SNOWY_HOUSE_IDENTIFIER)) {
      builder.pool(buildPool(0.2F, LeatheredArmorMaterial.LEATHERED_CHAIN));
      builder.pool(buildPool(0.1F, LeatheredArmorMaterial.LEATHERED_IRON));
      builder.pool(buildPool(0.05F, LeatheredArmorMaterial.LEATHERED_DIAMOND));
    }
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find a {@link LeatheredBootsItem} of the given {@link LeatheredArmorMaterial}.
   * 
   * @param chance
   * @param armorMaterial
   * @return loot pool.
   */
  private static Builder buildPool(float chance, LeatheredArmorMaterial armorMaterial) {
    return LootPool.builder()
      .rolls(ConstantLootNumberProvider.create(1))
      .conditionally(RandomChanceLootCondition.builder(chance))
      .with(ItemEntry.builder(LeatheredBoots.getLeatheredBoots(armorMaterial)))
      .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)));
  }
}
