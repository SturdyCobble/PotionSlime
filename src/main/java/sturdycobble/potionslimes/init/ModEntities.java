package sturdycobble.potionslimes.init;

import sturdycobble.potionslimes.PotionSlimes;
import sturdycobble.potionslimes.entity.PotionedSlimeEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, PotionSlimes.MODID);

    public static final RegistryObject<EntityType<PotionedSlimeEntity>> POTIONED_SLIME = ENTITIES.register(
            "potioned_slime", () -> EntityType.Builder.of(PotionedSlimeEntity::new, EntityClassification.MONSTER)
                    .build(new ResourceLocation(PotionSlimes.MODID, "potioned_slime").toString()));

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(POTIONED_SLIME.get(), MonsterEntity.createMonsterAttributes().build());
    }

}
