package sturdycobble.potionslimes;

import sturdycobble.potionslimes.init.ModEntities;
import sturdycobble.potionslimes.init.ModItems;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PotionSlimes.MODID)
@Mod.EventBusSubscriber(modid = PotionSlimes.MODID, bus = Bus.MOD)
public class PotionSlimes {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "potionslimes";

    public PotionSlimes() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntities.ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        modEventBus.addListener(EventPriority.NORMAL, ModEntities::registerEntityAttributes);

    }

}