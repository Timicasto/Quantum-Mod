package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.register.QuantumBaseBlocks;

public class BlockPoplarLeaves extends Block {
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
    public boolean isOpaqueCube(IBlockState state) { return true; }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.CUTOUT_MIPPED; }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
        new ItemStack(QuantumBaseBlocks.willowSaplingItemBlock);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }
    
}
