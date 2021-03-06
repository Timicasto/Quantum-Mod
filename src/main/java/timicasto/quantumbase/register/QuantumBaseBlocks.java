package timicasto.quantumbase.register;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.block.*;
import timicasto.quantumbase.fluid.*;
import timicasto.quantumbase.tile.TileEntityKeroseneLight;
import timicasto.quantumbase.tile.TileEntityMetalSmelter;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

import java.util.Objects;

public class QuantumBaseBlocks {
	
	public static Block willowWood = new BlockWillowWood();
	public static Block willowLeaves = new BlockWillowLeaves();
	public static Block willowSapling = new BlockWillowSaplings(0);
	public static Block poplarWood = new BlockPoplarWood();
	public static Block poplarLeaves = new BlockPoplarLeaves();
	public static Block poplarSapling = new BlockPoplarSapling(0);
	public static Block combustibleIce = new BlockCombustibleIce();
	public static Block blockCh4 = new BlockCH4();
	public static Block seaWater = new BlockSeaWater();
	public static Block petroleum = new LiquidPetroleumBlock();
	public static Block petroleumProcessor = new BlockPetroleumProcessor();
	public static Block keroseneLight = new BlockKeroseneLight();
	public static Block kerosene = new BlockKerosene();
	public static Block glow = new BlockGlow();
	public static Block hematite = new BlockHematite();
	public static Block magnetite = new BlockMagnetite();
	public static Block metalSmelter = new BlockMetalSmelter();

	public static Item willowWoodItemBlock = new ItemBlock(willowWood);
	public static Item willowLeavesItemBlock = new ItemBlock(willowLeaves);
	public static Item willowSaplingItemBlock = new ItemBlock(willowSapling);
	public static Item poplarWoodItemBlock = new ItemBlock(poplarWood);
	public static Item poplarLeavesItemBlock = new ItemBlock(poplarLeaves);
	public static Item poplarSaplingItemBlock = new ItemBlock(poplarSapling);
	public static Item combustibleIceItemBlock = new ItemBlock(combustibleIce);
	public static Item petroleumProcessorItemBlock = new ItemBlock(petroleumProcessor);
	public static Item keroseneLightItemBlock = new ItemBlock(keroseneLight);
	public static Item hematiteItemBlock = new ItemBlock(hematite);
	public static Item magnetiteItemBlock = new ItemBlock(magnetite);
	public static Item metalSmelterItemBlock = new ItemBlock(metalSmelter);

	public static void registerBlock(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				willowWood,
				willowLeaves,
				willowSapling,
				poplarLeaves,
				poplarSapling,
				poplarWood,
				combustibleIce,
				blockCh4,
				seaWater.setRegistryName("sea_water"),
				petroleum.setRegistryName("petroleum"),
				kerosene.setRegistryName("kerosene"),
				petroleumProcessor,
				keroseneLight.setRegistryName("kerosene_light").setUnlocalizedName("kerosene_light"),
				glow,
				hematite,
				magnetite,
				metalSmelter
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
				combustibleIceItemBlock.setRegistryName("combustible_ice"),
				petroleumProcessorItemBlock.setRegistryName("petroleum_processor"),
				keroseneLightItemBlock.setRegistryName("kerosene_light"),
				hematiteItemBlock.setRegistryName("hematite"),
				magnetiteItemBlock.setRegistryName("magnetite"),
				metalSmelterItemBlock.setRegistryName("metal_smelter")
		);
		GameRegistry.registerTileEntity(TileEntityPetroleumProcessor.class, new ResourceLocation(QuantumBase.MODID, "petroleum_processor"));
		GameRegistry.registerTileEntity(TileEntityKeroseneLight.class, new ResourceLocation(QuantumBase.MODID, "kerosene_light"));
		GameRegistry.registerTileEntity(TileEntityMetalSmelter.class, new ResourceLocation(QuantumBase.MODID, "metal_smelter"));
	}
	
	public static void registerItemModel(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(QuantumBase.MODID);
		itemModelFastRegister(
				willowWoodItemBlock,
				willowLeavesItemBlock,
				willowSaplingItemBlock,
				poplarWoodItemBlock,
				poplarLeavesItemBlock,
				poplarSaplingItemBlock,
				combustibleIceItemBlock,
				hematiteItemBlock,
				magnetiteItemBlock
		);
	}
	
	private static void itemModelFastRegister (Item... items) {
		for (Item i : items) {
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(Objects.requireNonNull(i.getRegistryName()), "inventory"));
		}
	}
	
}
