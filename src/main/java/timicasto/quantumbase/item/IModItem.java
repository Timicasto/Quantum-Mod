package timicasto.quantumbase.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.utils.annotation.Nullable;
import timicasto.quantumbase.utils.annotation.NotNull;

public interface IModItem <T extends Item> {
    String name();
    ItemStack newItem(int amount);
    @Nullable ItemBlock getItemBlock();
    @NotNull T getItem();
    @Nullable
    Block getBlock();
}
