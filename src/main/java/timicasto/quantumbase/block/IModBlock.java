package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public interface IModBlock <T extends Block> {
    String name();
    ItemStack getItem(int amount);
    ItemBlock toItemBlock();
    T getBlock();
}
