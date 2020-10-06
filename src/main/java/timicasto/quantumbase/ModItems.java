package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import timicasto.quantumbase.block.*;

public class ModItems {
    public static Block willowWood = new WillowWood();
    public static Item willowWoodItemBlock = new ItemBlock(willowWood);
    public static Block willowLeaves = new WillowLeaves();
    public static Item willowLeavesItemBlock = new ItemBlock(willowLeaves);
    public static Block willowSapling = new ModSaplings(0);
    public static Item willowSaplingItemBlock = new ItemBlock(willowSapling);
    public static Block poplarWood = new PoplarWood();
    public static Block poplarLeaves = new PoplarLeaves();
    public static Block poplarSapling = new PoplarSaplings(0);
    public static Item poplarWoodItemBlock = new ItemBlock(poplarWood);
    public static Item poplarLeavesItemBlock = new ItemBlock(poplarLeaves);
    public static Item poplarSaplingItemBlock = new ItemBlock(poplarSapling);


}
