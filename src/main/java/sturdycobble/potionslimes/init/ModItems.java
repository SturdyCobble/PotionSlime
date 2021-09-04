package sturdycobble.potionslimes.init;

import sturdycobble.potionslimes.PotionSlimes;
import sturdycobble.potionslimes.item.PotionSlimeBallItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotionSlimes.MODID);

    public static final RegistryObject<PotionSlimeBallItem> POTION_SLIME_BALL = ITEMS.register("potioned_slime_ball", () -> new PotionSlimeBallItem(new Item.Properties().tab(ModItemGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> POTION_SLIME_FEED = ITEMS.register("potioned_slime_feed", () -> new Item(new Item.Properties().tab(ModItemGroups.MOD_ITEM_GROUP)));

}
