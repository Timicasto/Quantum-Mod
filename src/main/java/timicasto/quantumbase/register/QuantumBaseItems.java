package timicasto.quantumbase.register;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import timicasto.quantumbase.item.ItemCombustibleIce;

public class QuantumBaseItems {
	
	public static Item combustibleIce = new ItemCombustibleIce();
	
	
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				combustibleIce
		);
	}
	
	public static void registerItemModel(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(combustibleIce, 0, new ModelResourceLocation(combustibleIce.getRegistryName(), "inventory"));
	}
}
