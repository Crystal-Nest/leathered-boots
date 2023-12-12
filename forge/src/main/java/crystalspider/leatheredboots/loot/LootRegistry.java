package crystalspider.leatheredboots.loot;

import com.mojang.serialization.Codec;

import crystalspider.leatheredboots.ModLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registry handler for loot modifiers.
 */
public class LootRegistry {
  /**
   * {@link Codec<? extends IGlobalLootModifier>} {@link DeferredRegister}.
   */
  private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModLoader.MOD_ID);
  /**
   * {@link LootItemConditionType} {@link DeferredRegister}.
   */
  private static final DeferredRegister<LootItemConditionType> LOOT_CONDITIONS = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, ModLoader.MOD_ID);

  /**
   * {@link RegistryObject} for {@link Codec} of {@link ChestLootModifier}.
   */
  public static final RegistryObject<Codec<ChestLootModifier>> CHEST_LOOT_MODIFIER = LOOT_MODIFIERS.register("chest_loot_modifier", ChestLootModifier.CODEC);
  /**
   * {@link RegistryObject} for {@link Codec} of {@link ArchaeologyLootModifier}.
   */
  public static final RegistryObject<Codec<ArchaeologyLootModifier>> ARCHAEOLOGY_LOOT_MODIFIER = LOOT_MODIFIERS.register("archaeology_loot_modifier", ArchaeologyLootModifier.CODEC);
  /**
   * {@link RegistryObject} for biomes check {@link LootItemConditionType}.
   */
  public static final RegistryObject<LootItemConditionType> BIOMES_CHECK = LOOT_CONDITIONS.register("biomes_check", () -> new LootItemConditionType(new BiomesCheck.Serializer()));

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    LOOT_MODIFIERS.register(bus);
    LOOT_CONDITIONS.register(bus);
  }
}
