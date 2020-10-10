package timicasto.quantumbase.block.special;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.RegistryHandler;
import timicasto.quantumbase.block.IModBlock;
import timicasto.quantumbase.utils.annotation.NotNull;

public abstract class ModBlockBush extends BlockBush implements IModBlock<BlockBush> {
    private final ItemBlock itemBlock;
    private static @NotNull String name;

    protected ModBlockBush(Material material, MapColor mapColor, @NotNull String registerName) {
        super(material, mapColor);
        this.setRegistryName(registerName);
        itemBlock = (ItemBlock) new ItemBlock(this).setRegistryName(registerName);
        name = registerName;
    }

    protected ModBlockBush(Material material, String registerName) {
        this(material, material.getMaterialMapColor(), registerName);
    }

    protected ModBlockBush(String registerName) {
        this(Material.PLANTS, registerName);
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
    public BlockBush getBlock() {
        return this;
    }

    public static ModBlockBush get() {
        return (ModBlockBush) RegistryHandler.getBlock(name).getBlock();
    }

    public static ItemBlock getItemBlock() {
        return RegistryHandler.getItemBlock(name);
    }
}
