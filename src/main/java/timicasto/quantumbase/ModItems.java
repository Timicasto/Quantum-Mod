package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import timicasto.quantumbase.block.WillowLeaves;
import timicasto.quantumbase.block.WillowSapling;
import timicasto.quantumbase.block.WillowWood;

import java.rmi.registry.Registry;
import java.util.HashMap;

public class ModItems {
    public static Block willowWood = new WillowWood();
    public static Item willowWoodItemBlock = new ItemBlock(willowWood);
    public static Block willowLeaves = new WillowLeaves();
    public static Item willowLeavesItemBlock = new ItemBlock(willowLeaves);
    public static Block willowSapling = new WillowSapling(0);
    public static Item willowSaplingItemBlock = new ItemBlock(willowSapling);

}
