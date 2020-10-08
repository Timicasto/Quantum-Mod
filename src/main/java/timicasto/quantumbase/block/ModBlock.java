package timicasto.quantumbase.block;


import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class ModBlock extends Block implements IModBlock<Block> {
    private final Item itemBlock;

    protected ModBlock(Material material, MapColor mapColor) {
        super(material, mapColor);
        itemBlock = new ItemBlock(this);
    }

    protected ModBlock(Material material) {
        this(material, material.getMaterialMapColor());
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
    public Block getBlock() {
        return this;
    }
}
