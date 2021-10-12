package quantumstudio.quantumbase.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.gui.BlastFurnaceScreen;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class EventHandler {

	@OnlyIn(Dist.CLIENT)
	@EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT)
	static class Client {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent e) {
			e.enqueueWork(() -> {
				MenuScreens.register(Contents.BLAST_FURNACE_MENU, BlastFurnaceScreen::new);
			});
		}
	}

}
