package sturdycobble.potionslimes.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import sturdycobble.potionslimes.entity.PotionedSlimeEntity;

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
        float scaling_factor = 0.999F;
        ms.scale(scaling_factor, scaling_factor, scaling_factor);
        ms.translate(0.0D, (double) 0.001F, 0.0D);
        float size = (float) entity.getSize();
        float squish = MathHelper.lerp(partialTickTime, entity.oSquish, entity.squish) / (size * 0.5F + 1.0F);
        float squish_scale = 1.0F / (squish + 1.0F);
        ms.scale(squish_scale * size, 1.0F / squish_scale * size, squish_scale * size);
    }

    @Override
    public ResourceLocation getTextureLocation(PotionedSlimeEntity entity) {
        return SLIME_LOCATION;
    }

}
