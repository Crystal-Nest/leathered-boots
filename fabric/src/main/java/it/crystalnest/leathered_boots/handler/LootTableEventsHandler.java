package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Objects;

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
  public static void handle(ResourceManager resourceManager, LootTables lootManager, ResourceLocation id, LootTable.Builder builder, LootTableSource source) {
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
      }
    }
  }

  /**
   * Builds a loot pool with the given random {@code chance} to find a {@link LeatheredBootsItem} of the given {@link LeatheredArmorMaterial}.
   *
   * @param chance chance to spawn the item.
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @return loot pool.
   */
  private static LootPool buildPool(float chance, LeatheredArmorMaterial armorMaterial) {
    return LootPool.lootPool()
      .setRolls(ConstantValue.exactly(1))
      .conditionally(LootItemRandomChanceCondition.randomChance(chance).build())
      .with(LootItem.lootTableItem(Objects.requireNonNull(LeatheredBootsManager.getBoots(Constants.MOD_ID, armorMaterial))).build())
      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
      .build();
  }
}
