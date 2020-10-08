package timicasto.quantumbase.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import timicasto.quantumbase.ModItems;
import timicasto.quantumbase.creative.TabLoader;

public class BlockPoplarLeaves extends ModBlock {

    public BlockPoplarLeaves() {
        super(Material.LEAVES);
        this.setRegistryName("poplar_leaves");
        this.setUnlocalizedName("poplar_leaves");
        this.setCreativeTab(TabLoader.envTab);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
        new ItemStack(ModItems.willowSaplingItemBlock);
    }

    @Override
    public ItemStack getItem(int amount) {
        return new ItemStack(this, amount);
    }

    @Override
    public String name() {
        return "poplar_leaves";
    }
}
