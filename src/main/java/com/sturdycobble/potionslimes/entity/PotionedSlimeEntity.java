package com.sturdycobble.potionslimes.entity;

import com.sturdycobble.potionslimes.init.ModItems;
import com.sturdycobble.potionslimes.utils.RGBColor;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionedSlimeEntity extends SlimeEntity {

    public static final DataParameter<String> POTION_TYPE = EntityDataManager.defineId(PotionedSlimeEntity.class,
            DataSerializers.STRING);
    public static int FEED_TICKS = 1600;
    private static int SIZE_LIMIT = 4;
    private int internal_ticks = 0;

    public PotionedSlimeEntity(EntityType<? extends SlimeEntity> type, World worldIn) {
        super(type, worldIn);
    }

    private static String potionToString(Potion potion) {
        System.out.println(ForgeRegistries.POTION_TYPES.getKey(potion).toString());
        return ForgeRegistries.POTION_TYPES.getKey(potion).toString();
    }

    private static Potion stringToPotion(String str) {
        System.out.println(str);
        return Potion.byName(str);
    }

    public boolean shrinkSize() {
        if (this.getSize() <= 1)
            return false;
        this.setSize(getSize() - 1, true);
        return true;
    }

    public ActionResultType feedSlime() {
        if (getSize() + 1 > SIZE_LIMIT || this.internal_ticks > 0)
            return ActionResultType.PASS;
        this.setSize(getSize() + 1, true);
        this.internal_ticks = FEED_TICKS;
        return ActionResultType.SUCCESS;
    }

    public Potion getPotionType() {
        return stringToPotion(this.entityData.get(POTION_TYPE));
    }

    public void setPotionType(Potion potion) {
        this.entityData.set(POTION_TYPE, potionToString(potion));
    }

    public RGBColor getEffectColor() {
        int colour = PotionUtils.getColor(getPotionType());
        return new RGBColor(colour, 0.8F);
    }

    public Potion extractPotion() {
        return this.shrinkSize() ? this.getPotionType() : Potions.EMPTY;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.BUCKET) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack slimeBallItemStack = new ItemStack(ModItems.POTION_SLIME_BALL.get());
            Potion p = this.extractPotion();
            if (p == Potions.EMPTY) {
                return ActionResultType.PASS;
            } else {
                slimeBallItemStack.getOrCreateTag().putString("Potion", potionToString(p));
            }
            if (!player.inventory.add(slimeBallItemStack)) {
                player.drop(slimeBallItemStack, false);
            }
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else if (itemstack.getItem() == ModItems.POTION_SLIME_FEED.get()) {
            itemstack.shrink(1);
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            return feedSlime();
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void remove(boolean keepData) {
        int i = this.getSize();
        Potion p = this.getPotionType();
        if (!this.level.isClientSide() && i > 1 && this.getHealth() <= 0.0F && this.isAlive()) {
            int j = 2 + this.random.nextInt(3);
            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                PotionedSlimeEntity slimeentity = (PotionedSlimeEntity) this.getType().create(this.level);
                if (this.hasCustomName()) {
                    slimeentity.setCustomName(this.getCustomName());
                }
                if (this.isPersistenceRequired()) {
                    slimeentity.setPersistenceRequired();
                }
                slimeentity.setInvulnerable(this.isInvulnerable());
                slimeentity.setSize(1, true);
                slimeentity.setPotionType(p);
                slimeentity.moveTo(this.getX() + (double) f, this.getY() + 0.5D,
                        this.getZ() + (double) f1, this.random.nextFloat() * 360.0F, 0.0F);
                this.level.addFreshEntity(slimeentity);
            }
        }
        this.setSize(1, true);

        super.remove(keepData);
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if (this.internal_ticks > 0) {
            --this.internal_ticks;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POTION_TYPE, potionToString(Potions.WATER));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Potion", potionToString(getPotionType()));
        tag.putInt("FeedTicks", this.internal_ticks);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        this.setPotionType(stringToPotion(tag.getString("Potion")));
        this.internal_ticks = tag.getInt("FeedTicks");

        super.readAdditionalSaveData(tag);
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean canAttackType(EntityType<?> entity) {
        return false;
    }

}
