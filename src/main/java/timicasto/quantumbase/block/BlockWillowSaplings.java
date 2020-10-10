package timicasto.quantumbase.block;

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
import net.minecraftforge.event.terraingen.TerrainGen;
import timicasto.quantumbase.block.special.ModBlockBush;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.environment.GenTree;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;

import java.util.Random;

public class BlockWillowSaplings extends ModBlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    public int type;

    @ManualRegisterConstructor
    public BlockWillowSaplings(int i) {
        super("willow_sapling");
        this.type = i;
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE,0));
        this.setUnlocalizedName("willow_sapling");
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
        if (!TerrainGen.saplingGrowTree(worldIn,rand,pos)) {
            return;
        }

        WorldGenerator worldGenerator;

        if (type == 0) {
            worldGenerator = new GenTree().willowTree;
        } else {
            worldGenerator = rand.nextInt(10) == 0? new WorldGenBigTree(true) : new WorldGenTrees(true);
        }

        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        if (!worldGenerator.generate(worldIn, rand, pos)) {
            worldIn.setBlockState(pos, state, 4);
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
