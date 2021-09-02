package com.sturdycobble.potionslimes.init;

import java.util.function.Supplier;

import com.sturdycobble.potionslimes.PotionSlimes;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroups {

    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(PotionSlimes.MODID, () -> new ItemStack(Items.SLIME_BALL));

    public static class ModItemGroup extends ItemGroup {

        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack makeIcon() {
            return iconSupplier.get();
        }

    }

}
