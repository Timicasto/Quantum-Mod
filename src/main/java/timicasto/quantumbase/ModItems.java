package timicasto.quantumbase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import timicasto.quantumbase.block.WillowWood;

public class ModItems {
    public static Block willowWood = new WillowWood();
    public static Item willowWoodItemBlock = new ItemBlock(willowWood);
}
