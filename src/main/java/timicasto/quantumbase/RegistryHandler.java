package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(ModItems.willowWood);
        event.getRegistry().register(ModItems.willowLeaves);
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ModItems.willowWoodItemBlock.setRegistryName("willow_wood"));
        event.getRegistry().register(ModItems.willowLeavesItemBlock.setRegistryName("willow_leaves"));
    }
}
