package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModItems.willowWood);
        event.getRegistry().register(ModItems.willowLeaves);
        event.getRegistry().register(ModItems.willowSapling);
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ModItems.willowWoodItemBlock.setRegistryName("willow_wood"));
        event.getRegistry().register(ModItems.willowLeavesItemBlock.setRegistryName("willow_leaves"));
        event.getRegistry().register(ModItems.willowSaplingItemBlock.setRegistryName("willow_sapling"));
    }

    @SubscribeEvent
    public static void registerItemModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowWoodItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowLeavesItemBlock,0,new ModelResourceLocation(ModItems.willowLeavesItemBlock.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.willowWoodItemBlock,0,new ModelResourceLocation(ModItems.willowSaplingItemBlock.getRegistryName(),"inventory"));
    }
}
