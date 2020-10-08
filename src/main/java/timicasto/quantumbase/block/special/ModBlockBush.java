package timicasto.quantumbase.block.special;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.block.IModBlock;

public abstract class ModBlockBush extends BlockBush implements IModBlock<BlockBush> {
    private final Item itemBlock;

    protected ModBlockBush(Material material, MapColor mapColor) {
        super(material, mapColor);
        itemBlock = new ItemBlock(this);
    }

    protected ModBlockBush(Material material) {
        this(material, material.getMaterialMapColor());
    }

    protected ModBlockBush() {
        this(Material.PLANTS);
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
    public BlockBush getBlock() {
        return this;
    }
}
