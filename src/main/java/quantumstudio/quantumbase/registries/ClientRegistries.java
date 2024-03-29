package quantumstudio.quantumbase.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import quantumstudio.quantumbase.Constants;
import quantumstudio.quantumbase.gui.BlastFurnaceScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistries {
	@SubscribeEvent
	public static void renderType(FMLClientSetupEvent e) {
		e.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(Contents.PETROLEUM_STILL, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Contents.PETROLEUM_FLOWING, RenderType.translucent());
		});
	}

	@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MODID)
	public static class Client {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			event.enqueueWork(() -> {
				MenuScreens.register(Contents.BLAST_FURNACE_MENU, BlastFurnaceScreen::new);
			});
			MenuScreens.register(Contents.BLAST_FURNACE_MENU, BlastFurnaceScreen::new);
		}
	}
}
