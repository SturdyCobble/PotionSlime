package sturdycobble.potionslimes.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import sturdycobble.potionslimes.entity.PotionedSlimeEntity;
import sturdycobble.potionslimes.utils.RGBColor;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class PotionedSlimeGelLayer<T extends LivingEntity> extends LayerRenderer<T, PotionedSlimeModel<T>> {

    private final EntityModel<T> slimeModel = new PotionedSlimeModel<>(0);

    public PotionedSlimeGelLayer(IEntityRenderer<T, PotionedSlimeModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack ms, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.slimeModel);
            PotionedSlimeEntity slimeentity = (entity instanceof PotionedSlimeEntity) ? (PotionedSlimeEntity) entity
                    : null;
            RGBColor color = slimeentity != null ? slimeentity.getEffectColor() : new RGBColor(0.0F);
            this.slimeModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.slimeModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = buffer
                    .getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity)));
            this.slimeModel.renderToBuffer(ms, ivertexbuilder, packedLight, LivingRenderer.getOverlayCoords(entity, 0.0F),
                    0.5F * color.getRed() + 0.5F, 0.5F * color.getGreen() + 0.5F, 0.5F * color.getBlue() + 0.5F, color.getAlpha());
        }
    }

}
