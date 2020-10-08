package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import timicasto.quantumbase.block.*;
// import timicasto.quantumbase.fluid.LiquidPetroleum;
// import timicasto.quantumbase.fluid.LiquidPetroleumBlock;

public class ModItems {
    public static Block willowWood = new BlockWillowWood();
    public static Item willowWoodItemBlock = new ItemBlock(willowWood);
    public static Block willowLeaves = new BlockWillowLeaves();
    public static Item willowLeavesItemBlock = new ItemBlock(willowLeaves);
    public static Block willowSapling = new BlockModSaplings(0);
    public static Item willowSaplingItemBlock = new ItemBlock(willowSapling);
    public static Block poplarWood = new BlockPoplarWood();
    public static Block poplarLeaves = new BlockPoplarLeaves();
    public static Block poplarSapling = new BlockPoplarSaplings(0);
    public static Item poplarWoodItemBlock = new ItemBlock(poplarWood);
    public static Item poplarLeavesItemBlock = new ItemBlock(poplarLeaves);
    public static Item poplarSaplingItemBlock = new ItemBlock(poplarSapling);
    // public static Fluid petroleum = new LiquidPetroleum();
    // public static Block petroleumBlock = new LiquidPetroleumBlock();
    public static Block combustibleIce = new BlockCombustibleIce();
    public static Item combustibleIceItemBlock = new ItemBlock(combustibleIce);
}
