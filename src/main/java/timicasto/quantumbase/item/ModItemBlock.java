package timicasto.quantumbase.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.RegistryHandler;
import timicasto.quantumbase.utils.annotation.NotNull;
import timicasto.quantumbase.utils.annotation.Nullable;

public class ModItemBlock extends ItemBlock implements IModItem<ItemBlock> {
    private static @NotNull String name;

    protected ModItemBlock(Block block, String registerName) {
        super(block);
        name = registerName;
        this.setRegistryName(name);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ItemStack newItem(int amount) {
        return new ItemStack(this, amount);
    }

    @Override
    public @Nullable ItemBlock getItemBlock() {
        return this;
    }

    @Override
    public @NotNull ItemBlock getItem() {
        return this;
    }

    public static ModItemBlock get() {
        return (ModItemBlock) RegistryHandler.getItem(name).getItem();
    }
}
