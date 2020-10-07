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

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModItems.willowWood);
        event.getRegistry().register(ModItems.willowLeaves);
        event.getRegistry().register(ModItems.willowSapling);
        event.getRegistry().register(ModItems.poplarLeaves);
        event.getRegistry().register(ModItems.poplarSapling);
        event.getRegistry().register(ModItems.poplarWood);
        event.getRegistry().register(ModItems.combustibleIce);
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ModItems.willowWoodItemBlock.setRegistryName("willow_wood"));
        event.getRegistry().register(ModItems.willowLeavesItemBlock.setRegistryName("willow_leaves"));
        event.getRegistry().register(ModItems.willowSaplingItemBlock.setRegistryName("willow_sapling"));
        event.getRegistry().register(ModItems.poplarLeavesItemBlock.setRegistryName("poplar_leaves"));
        event.getRegistry().register(ModItems.poplarSaplingItemBlock.setRegistryName("poplar_sapling"));
        event.getRegistry().register(ModItems.poplarWoodItemBlock.setRegistryName("poplar_wood"));
        event.getRegistry().register(ModItems.combustibleIcecItemBlock);
        event.getRegistry().register(ModItems.getCombustibleIceItemBlock.setRegistryName("combustible_ice"));
    }

    @SubscribeEvent
    public static void registerItemModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowLeavesItemBlock,0,new ModelResourceLocation(ModItems.willowLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarLeavesItemBlock,0,new ModelResourceLocation(ModItems.poplarLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarSaplingItemBlock,0,new ModelResourceLocation(ModItems.poplarSaplingItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.poplarWoodItemBlock,0,new ModelResourceLocation(ModItems.poplarWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.combustibleIcecItemBlock,0,new ModelResourceLocation(ModItems.combustibleIcecItemBlock.getRegistryName(),"inventory"));
    }
}
