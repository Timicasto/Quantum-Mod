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
        ModItems.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        ModItems.registerItems(event.getRegistry());
    }
}
