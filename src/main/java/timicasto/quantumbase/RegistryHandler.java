package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.register.QuantumBaseBlocks;
import timicasto.quantumbase.register.QuantumBaseItems;

@Mod.EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void registerBlock(RegistryEvent.Register<Block> event) {
		QuantumBaseBlocks.registerBlock(event);
	}
	
	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		QuantumBaseBlocks.registerItem(event);
		QuantumBaseItems.registerItem(event);
	}
	
	@SubscribeEvent
	public static void registerItemModel(ModelRegistryEvent event) {
		QuantumBaseBlocks.registerItemModel(event);
		QuantumBaseItems.registerItemModel(event);
	}
	
	@SubscribeEvent
	public static void onEntityRegistation(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().register(EntityEntryBuilder.create()
				.entity(PrimedIce.class)
				.id(new ResourceLocation(QuantumBase.MODID, "PrimedIce"), 233)
				.name("PrimedIce")
				.tracker(80, 3, false)
				.build()
		);
	}
}
