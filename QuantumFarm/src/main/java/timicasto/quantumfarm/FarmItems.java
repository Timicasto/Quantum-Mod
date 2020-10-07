package timicasto.quantumfarm;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import timicasto.quantumfarm.block.AppleLeaves;
import timicasto.quantumfarm.block.AppleSapling;
import timicasto.quantumfarm.block.AppleWood;

public class FarmItems {
    public static Block appleWood = new AppleWood();
    public static Block appleLeaves = new AppleLeaves();
    public static Block appleSapling = new AppleSapling(0);
    public static Item appleWoodItemBlock = new ItemBlock(appleWood);
    public static Item appleLeavesItemBlock = new ItemBlock(appleLeaves);
    public static Item appleSaplingItemBlock = new ItemBlock(appleSapling);
}
