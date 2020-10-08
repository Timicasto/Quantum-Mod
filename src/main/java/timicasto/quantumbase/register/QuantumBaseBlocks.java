package timicasto.quantumbase.register;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import timicasto.quantumbase.block.*;

public class QuantumBaseBlocks {
	
	public static Block willowWood = new BlockWillowWood();
	public static Block willowLeaves = new BlockWillowLeaves();
	public static Block willowSapling = new BlockWillowSaplings(0);
	public static Block poplarWood = new BlockPoplarWood();
	public static Block poplarLeaves = new BlockPoplarLeaves();
	public static Block poplarSapling = new BlockPoplarSapling(0);
	public static Block combustibleIce = new BlockCombustibleIce();
	
	public static Item willowWoodItemBlock = new ItemBlock(willowWood);
	public static Item willowLeavesItemBlock = new ItemBlock(willowLeaves);
	public static Item willowSaplingItemBlock = new ItemBlock(willowSapling);
	public static Item poplarWoodItemBlock = new ItemBlock(poplarWood);
	public static Item poplarLeavesItemBlock = new ItemBlock(poplarLeaves);
	public static Item poplarSaplingItemBlock = new ItemBlock(poplarSapling);
	public static Item combustibleIceItemBlock = new ItemBlock(combustibleIce);
	
	public static void registerBlock(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				willowWood,
				willowLeaves,
				willowSapling,
				poplarLeaves,
				poplarSapling,
				poplarWood,
				combustibleIce
		);
	}
	
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				willowWoodItemBlock.setRegistryName("willow_wood"),
				willowLeavesItemBlock.setRegistryName("willow_leaves"),
				willowSaplingItemBlock.setRegistryName("willow_sapling"),
				poplarLeavesItemBlock.setRegistryName("poplar_leaves"),
				poplarSaplingItemBlock.setRegistryName("poplar_sapling"),
				poplarWoodItemBlock.setRegistryName("poplar_wood"),
				combustibleIceItemBlock.setRegistryName("combustible_ice")
		);
	}
	
	public static void registerItemModel(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(willowWoodItemBlock, 0, new ModelResourceLocation(willowWoodItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(willowLeavesItemBlock, 0, new ModelResourceLocation(willowLeavesItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(willowSaplingItemBlock, 0, new ModelResourceLocation(willowSaplingItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(poplarLeavesItemBlock, 0, new ModelResourceLocation(poplarLeavesItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(poplarSaplingItemBlock, 0, new ModelResourceLocation(poplarSaplingItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(poplarWoodItemBlock, 0, new ModelResourceLocation(poplarWoodItemBlock.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(combustibleIceItemBlock, 0, new ModelResourceLocation(combustibleIceItemBlock.getRegistryName(), "inventory"));
	}
	
}
