package timicasto.quantumfarm.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import timicasto.quantumbase.ModItems;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumfarm.creative.FarmTabLoader;

public class AppleLeaves extends Block {
    public AppleLeaves() {
        super(Material.LEAVES);
        this.setRegistryName("apple_leaves");
        this.setUnlocalizedName("apple_leaves");
        this.setCreativeTab(FarmTabLoader.farmTab);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);

    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
        new ItemStack(ModItems.willowSaplingItemBlock);
    }
}
