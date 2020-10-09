package timicasto.quantumbase.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.RegistryHandler;
import timicasto.quantumbase.utils.annotation.NotNull;
import timicasto.quantumbase.utils.annotation.Nullable;

public class ModItem extends Item implements IModItem<Item> {
    private static @NotNull String name;

    protected ModItem(String registerName) {
        name = registerName;
        this.setRegistryName(name);
    }

    public String name() {
        return name;
    }

    @Override
    public ItemStack newItem(int amount) {
        return new ItemStack(this, amount);
    }

    @Override
    public @Nullable ItemBlock getItemBlock() {
        return null;
    }

    @Override
    public @NotNull Item getItem() {
        return this;
    }

    @Override
    public @Nullable Block getBlock() {
        return null;
    }

    public static ModItem get() {
        return (ModItem) RegistryHandler.getItem(name).getItem();
    }

}
