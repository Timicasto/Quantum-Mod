package quantumstudio.quantumbase.registries;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.world.OreGenerations;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Events {
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent e) {
		e.enqueueWork(() -> {
			OreGenerations.initFeatures();
			Constants.logger.info("OreGenFeatures Initialized");
		});
		MinecraftForge.EVENT_BUS.addListener(OreGenerations::onBiomeLoading);
	}
}
