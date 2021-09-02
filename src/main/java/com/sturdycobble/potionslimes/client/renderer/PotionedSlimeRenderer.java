package com.sturdycobble.potionslimes.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sturdycobble.potionslimes.entity.PotionedSlimeEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class PotionedSlimeRenderer extends MobRenderer<PotionedSlimeEntity, PotionedSlimeModel<PotionedSlimeEntity>> {

    private static final ResourceLocation SLIME_LOCATION = new ResourceLocation("textures/entity/slime/slime.png");

    public PotionedSlimeRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new PotionedSlimeModel<>(16), 0.25F);
        this.addLayer(new PotionedSlimeGelLayer<>(this));
    }

    @Override
    public void render(PotionedSlimeEntity entity, float entityYaw, float partialTicks, MatrixStack ms,
                       IRenderTypeBuffer buffer, int packedLight) {
        this.shadowRadius = 0.25F * (float) entity.getSize();
        super.render(entity, entityYaw, partialTicks, ms, buffer, packedLight);
    }

    @Override
    protected void scale(PotionedSlimeEntity entity, MatrixStack ms, float partialTickTime) {
        float f = 0.999F;
        ms.scale(f, f, f);
        ms.translate(0.0D, (double) 0.001F, 0.0D);
        float f1 = (float) entity.getSize();
        float f2 = MathHelper.lerp(partialTickTime, entity.oSquish, entity.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        ms.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(PotionedSlimeEntity entity) {
        return SLIME_LOCATION;
    }

}
