package timicasto.quantumbase.block.special;

import net.minecraft.block.BlockIce;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.block.IModBlock;

public abstract class ModBlockIce extends BlockIce implements IModBlock<BlockIce> {
    private final Item itemBlock;

    protected ModBlockIce() {
        super();
        itemBlock = new ItemBlock(this);
    }

    @Override
    public ItemStack getItem(int amount) {
        return new ItemStack(this, amount);
    }

    @Override
    public Item toItemBlock() {
        return itemBlock;
    }

    @Override
    public abstract String name();

    @Override
    public BlockIce getBlock() {
        return this;
    }
}
