package crystalspider.leatheredboots;

import com.mojang.serialization.Codec;

import crystalspider.leatheredboots.armor.LeatheredArmorMaterials;
import crystalspider.leatheredboots.items.LeatheredBootsItem;
import crystalspider.leatheredboots.loot.ChestLootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Leathered Boots mod loader.
 */
@Mod(LeatheredBootsLoader.MODID)
public class LeatheredBootsLoader {
  /**
   * ID of this mod.
   */
  public static final String MODID = "leatheredboots";

  /**
   * Base ID for leathered boots.
   */
  public static final String LEATHERED_BOOTS_ID = "leathered_boots";

  /**
   * Network channel protocol version.
   */
  public static final String PROTOCOL_VERSION = "1.19-1.0";
  /**
   * {@link SimpleChannel} instance for compatibility client-server.
   */
  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

  /**
   * {@link CreativeModTab} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final CreativeModeTab LEATHERED_BOOTS_TAB = new CreativeModeTab(MODID) {
    @Override
    public ItemStack makeIcon() {
      return NETHERITE_LEATHERED_BOOTS.get().getDefaultInstance();
    }
  }.setRecipeFolderName(LEATHERED_BOOTS_ID);

  /**
   * {@link Item Items} {@link DeferredRegister deferred register}.
   */
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
  /**
   * {@link Codec<? extends IGlobalLootModifier>} {@link DeferredRegister deferred register}.
   */
  public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_CHAIN Chain}.
   */
  public static final RegistryObject<LeatheredBootsItem> CHAINMAIL_LEATHERED_BOOTS = registerLeatheredBootsItem("chainmail", LeatheredArmorMaterials.LEATHERED_CHAIN, (new Properties()).tab(LEATHERED_BOOTS_TAB));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_IRON Iron}.
   */
  public static final RegistryObject<LeatheredBootsItem> IRON_LEATHERED_BOOTS = registerLeatheredBootsItem("iron", LeatheredArmorMaterials.LEATHERED_IRON, (new Properties()).tab(LEATHERED_BOOTS_TAB));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_DIAMOND Diamond}.
   */
  public static final RegistryObject<LeatheredBootsItem> DIAMOND_LEATHERED_BOOTS = registerLeatheredBootsItem("diamond", LeatheredArmorMaterials.LEATHERED_DIAMOND, (new Properties()).tab(LEATHERED_BOOTS_TAB));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_GOLD Gold}.
   */
  public static final RegistryObject<LeatheredBootsItem> GOLDEN_LEATHERED_BOOTS = registerLeatheredBootsItem("golden", LeatheredArmorMaterials.LEATHERED_GOLD, (new Properties()).tab(LEATHERED_BOOTS_TAB));
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_NETHERITE Netherite}.
   */
  public static final RegistryObject<LeatheredBootsItem> NETHERITE_LEATHERED_BOOTS = registerLeatheredBootsItem("netherite", LeatheredArmorMaterials.LEATHERED_NETHERITE, (new Properties()).tab(LEATHERED_BOOTS_TAB).fireResistant());
  /**
   * {@link RegistryObject} for {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#ENDERITE Enderite}.
   */
  // public static final RegistryObject<LeatheredBootsItem> ENDERITE_LEATHERED_BOOTS = registerLeatheredBootsItem("enderite", LeatheredArmorMaterials.ENDERITE, (new Properties()).tab(LEATHERED_BOOTS_TAB).fireResistant());

  /**
   * {@link RegistryObject} for {@link Codec} of {@link ChestLootModifier}.
   */
  public static final RegistryObject<Codec<ChestLootModifier>> CHEST_LOOT_MODIFIER = LOOT_MODIFIERS.register("chest_loot_modifier", ChestLootModifier.CODEC);
  
  /**
   * Returns the registered {@link LeatheredBootsItem}.
   * 
   * @param idPrefix prefix for the ID.
   * @param armorMaterial {@link ArmorMaterial} the boots are made of.
   * @return the registered {@link LeatheredBootsItem}.
   */
  private static final RegistryObject<LeatheredBootsItem> registerLeatheredBootsItem(String idPrefix, ArmorMaterial armorMaterial, Properties properties) {
    return ITEMS.register(idPrefix + "_" + LEATHERED_BOOTS_ID, () -> new LeatheredBootsItem(armorMaterial, properties));
  }

  public LeatheredBootsLoader() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ITEMS.register(modEventBus);
    LOOT_MODIFIERS.register(modEventBus);
  }
}
