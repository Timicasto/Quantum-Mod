package timicasto.quantumbase.block;


import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.RegistryHandler;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;
import timicasto.quantumbase.utils.annotation.NotNull;

public abstract class ModBlock extends Block implements IModBlock<Block> {
    private static @NotNull String name;
    private final ItemBlock itemBlock;

    @ManualRegisterConstructor
    protected ModBlock(Material material, MapColor mapColor, @NotNull String registerName) {
        super(material, mapColor);
        itemBlock = (ItemBlock) new ItemBlock(this).setRegistryName(registerName);
        name = registerName;
        this.setRegistryName(registerName);
    }

    @ManualRegisterConstructor
    protected ModBlock(Material material, @NotNull String registerName) {
        this(material, material.getMaterialMapColor(), registerName);
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
    public Block getBlock() {
        return this;
    }

    public static ModBlock get() {
        return (ModBlock) RegistryHandler.getBlock(name).getBlock();
    }

    public static ItemBlock getItemBlock() {
        return RegistryHandler.getItemBlock(name);
    }
}
