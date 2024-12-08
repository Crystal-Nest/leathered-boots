package it.crystalnest.leathered_boots.item;

import it.crystalnest.leathered_boots.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

/**
 * Leather upgrade smithing template item.
 */
public class LeatherUpgradeSmithingTemplateItem extends SmithingTemplateItem {
  /**
   * @param properties item properties.
   */
  public LeatherUpgradeSmithingTemplateItem(Properties properties) {
    super(
      Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "smithing_template.leather_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE),
      Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "smithing_template.leather_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE),
      Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "smithing_template.leather_upgrade.base_slot_description"))),
      Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "smithing_template.leather_upgrade.additions_slot_description"))),
      List.of(ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots")),
      List.of(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "item/empty_slot_leather")),
      properties.rarity(Rarity.UNCOMMON)
    );
  }
}
