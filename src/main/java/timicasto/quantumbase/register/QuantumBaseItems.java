package timicasto.quantumbase.register;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import timicasto.quantumbase.item.ItemAsphalt;
import timicasto.quantumbase.item.ItemCombustibleIce;
import timicasto.quantumbase.item.ItemDebugTool;
import timicasto.quantumbase.item.ItemParaffin;

public class QuantumBaseItems {
	
	public static Item combustibleIce = new ItemCombustibleIce();
	public static Item paraffin = new ItemParaffin();
	public static Item asphalt = new ItemAsphalt();
	public static Item debugTool = new ItemDebugTool();
	
	
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				combustibleIce,
				paraffin,
				asphalt,
				debugTool
		);
	}
	
	public static void registerItemModel(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(combustibleIce, 0, new ModelResourceLocation(combustibleIce.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(paraffin, 0, new ModelResourceLocation(paraffin.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(asphalt, 0, new ModelResourceLocation(asphalt.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(debugTool, 0, new ModelResourceLocation(debugTool.getRegistryName(), "inventory"));
	}
}
