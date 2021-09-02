package com.sturdycobble.potionslimes;

import com.sturdycobble.potionslimes.client.renderer.PotionedSlimeRenderer;
import com.sturdycobble.potionslimes.entity.PotionedSlimeEntity;
import com.sturdycobble.potionslimes.init.ModEntities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = PotionSlimes.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class PotionSlimesClient {

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.POTIONED_SLIME.get(), new IRenderFactory<PotionedSlimeEntity>() {
            @Override
            public EntityRenderer<PotionedSlimeEntity> createRenderFor(EntityRendererManager manager) {
                return new PotionedSlimeRenderer(manager);
            }
        });
    }

}
