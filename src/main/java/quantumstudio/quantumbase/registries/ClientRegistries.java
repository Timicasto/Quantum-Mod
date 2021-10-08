package quantumstudio.quantumbase.registries;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistries {
	@SubscribeEvent
	public static void renderType(FMLClientSetupEvent e) {
		e.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(Contents.PETROLEUM_STILL, RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Contents.PETROLEUM_FLOWING, RenderType.translucent());
		});
	}
}
