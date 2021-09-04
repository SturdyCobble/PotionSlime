package sturdycobble.potionslimes.init;

import sturdycobble.potionslimes.PotionSlimes;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup MOD_ITEM_GROUP = new ItemGroup(PotionSlimes.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.POTION_SLIME_BALL.get());
        }
    };

}
