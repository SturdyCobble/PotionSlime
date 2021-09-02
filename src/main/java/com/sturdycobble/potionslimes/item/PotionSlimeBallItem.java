package com.sturdycobble.potionslimes.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PotionSlimeBallItem extends Item {

    public PotionSlimeBallItem(Properties builder) {
        super(builder);
    }

    public static ItemStack getPotionItem(ItemStack item) {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), PotionUtils.getPotion(item));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.WATER);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> lores, ITooltipFlag flag) {
        PotionUtils.addPotionTooltip(stack, lores, 1.0F);
    }

}
