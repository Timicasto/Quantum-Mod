package timicasto.quantumbase.block.special;

import net.minecraft.block.BlockIce;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.RegistryHandler;
import timicasto.quantumbase.block.IModBlock;
import timicasto.quantumbase.utils.annotation.NotNull;

public abstract class ModBlockIce extends BlockIce implements IModBlock<BlockIce> {
    private final ItemBlock itemBlock;
    private static @NotNull String name;

    protected ModBlockIce(@NotNull String registerName) {
        super();
        this.setRegistryName(registerName);
        itemBlock = (ItemBlock) new ItemBlock(this).setRegistryName(registerName);
        name = registerName;
    }

    @Override
    public ItemStack getItem(int amount) {
        return new ItemStack(this, amount);
    }

    @Override
    public ItemBlock toItemBlock() {
        return itemBlock;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BlockIce getBlock() {
        return this;
    }

    public static ModBlockIce get() {
        return (ModBlockIce) RegistryHandler.getBlock(name).getBlock();
    }

    public static ItemBlock getItemBlock() {
        return RegistryHandler.getItemBlock(name);
    }
}
