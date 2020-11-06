package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.tile.TileEntityMetalSmelter;

public class BlockMetalSmelter extends Block {
    public BlockMetalSmelter() {
        super(Material.ROCK);
        setRegistryName("metal_smelter");
        setUnlocalizedName("metal_smelter");
        setCreativeTab(TabLoader.envTab);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityMetalSmelter();
    }
}
