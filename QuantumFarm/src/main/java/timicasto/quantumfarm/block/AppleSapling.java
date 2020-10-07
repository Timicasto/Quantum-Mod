package timicasto.quantumfarm.block;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.environment.GenTree;
import timicasto.quantumfarm.world.FarmGenTree;

import java.util.Random;

public class AppleSapling extends BlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public int type;

    public AppleSapling(int i) {
        this.type=i;
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE,0));
        this.setRegistryName("apple_sapling");
        this.setUnlocalizedName("apple_sapling");
        this.setCreativeTab(TabLoader.envTab);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return type != 3 ? super.canPlaceBlockAt(worldIn,pos) && soil.getBlock().canSustainPlant(soil,worldIn,pos.down(), EnumFacing.UP,this) : soil.getBlock() == Blocks.DIRT;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn,pos,state,rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn,pos,state,rand);
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(state.getValue(STAGE) == 0) {
            worldIn.setBlockState(pos,state.cycleProperty(STAGE),4);
        } else {
            generateTree(worldIn,pos,state,rand);
        }
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn,rand,pos))
            return;
        WorldGenerator worldGenerator = rand.nextInt(10) == 0? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (type) {
            case 0:
                worldGenerator = new FarmGenTree().appleTree;
                break;
        }

        IBlockState iBlockState2 = Blocks.AIR.getDefaultState();
        worldIn.setBlockState(pos,iBlockState2,4);

        if (!worldGenerator.generate(worldIn,rand,pos.add(i,0,j))) {
            worldIn.setBlockState(pos,state,4);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal( World worldIn,  Random rand,  BlockPos pos,  IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow( World worldIn,  Random rand,  BlockPos pos,  IBlockState state) {
        this.grow(worldIn, pos, state, rand);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }

}
