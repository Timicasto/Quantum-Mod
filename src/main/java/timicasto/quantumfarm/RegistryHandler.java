package timicasto.quantumfarm;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import timicasto.quantumbase.ModItems;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(FarmItems.appleLeaves);
        event.getRegistry().register(FarmItems.appleSapling);
        event.getRegistry().register(FarmItems.appleWood);
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(FarmItems.appleLeavesItemBlock.setRegistryName("apple_leaves"));
        event.getRegistry().register(FarmItems.appleSaplingItemBlock.setRegistryName("apple_sapling"));
        event.getRegistry().register(FarmItems.appleWoodItemBlock.setRegistryName("apple_wood"));
    }

    @SubscribeEvent
    public static void registerItemModel(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(FarmItems.appleWoodItemBlock,0,new ModelResourceLocation(FarmItems.appleWoodItemBlock.getRegistryName(),"inventory"));

    }

}
