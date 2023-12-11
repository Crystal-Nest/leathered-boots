package crystalspider.leatheredboots.item;

import java.util.List;

import crystalspider.leatheredboots.ModLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

/**
 * Leather upgrade smithing template item.
 */
public class LeatherUpgradeSmithingTemplateItem extends SmithingTemplateItem {
  public LeatherUpgradeSmithingTemplateItem() {
    super(
      Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ModLoader.MOD_ID, "smithing_template.leather_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE), 
      Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ModLoader.MOD_ID, "smithing_template.leather_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE), 
      Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(ModLoader.MOD_ID, "leather_upgrade"))).withStyle(ChatFormatting.GRAY), 
      Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ModLoader.MOD_ID, "smithing_template.leather_upgrade.base_slot_description"))),
      Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(ModLoader.MOD_ID, "smithing_template.leather_upgrade.additions_slot_description"))), 
      List.of(new ResourceLocation("item/empty_armor_slot_boots")), 
      List.of(new ResourceLocation(ModLoader.MOD_ID, "item/empty_slot_leather"))
    );
  }
}
