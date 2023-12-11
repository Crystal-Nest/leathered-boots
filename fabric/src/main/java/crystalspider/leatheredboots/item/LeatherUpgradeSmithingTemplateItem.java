package crystalspider.leatheredboots.item;

import java.util.List;

import crystalspider.leatheredboots.ModLoader;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

/**
 * Leather upgrade smithing template item.
 */
public class LeatherUpgradeSmithingTemplateItem extends SmithingTemplateItem {
  public LeatherUpgradeSmithingTemplateItem() {
    super(
      Text.translatable(Util.createTranslationKey("item", new Identifier(ModLoader.MOD_ID, "smithing_template.leather_upgrade.applies_to"))).formatted(Formatting.BLUE), 
      Text.translatable(Util.createTranslationKey("item", new Identifier(ModLoader.MOD_ID, "smithing_template.leather_upgrade.ingredients"))).formatted(Formatting.BLUE), 
      Text.translatable(Util.createTranslationKey("upgrade", new Identifier(ModLoader.MOD_ID, "leather_upgrade"))).formatted(Formatting.GRAY), 
      Text.translatable(Util.createTranslationKey("item", new Identifier(ModLoader.MOD_ID, "smithing_template.leather_upgrade.base_slot_description"))),
      Text.translatable(Util.createTranslationKey("item", new Identifier(ModLoader.MOD_ID, "smithing_template.leather_upgrade.additions_slot_description"))), 
      List.of(new Identifier("item/empty_armor_slot_boots")), 
      List.of(new Identifier(ModLoader.MOD_ID, "item/empty_slot_leather"))
    );
  }
}
