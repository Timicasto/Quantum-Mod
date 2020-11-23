package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.entity.PrimedIce;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCH4 extends Block {

    private Logger logger = LogManager.getLogger();

    public BlockCH4() {
        super(Material.AIR);
        setRegistryName("ch4");
        setUnlocalizedName("ch4");
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isCollidable()
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int cux = pos.getX();
        int cuy = pos.getY();
        int cuz = pos.getZ();
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy-1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy-1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy-1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy-1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy-1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy-1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy-1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy-1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy-1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        //
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
//
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy+1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy+1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux-1, cuy+1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy+1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy+1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux, cuy+1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy+1, cuz-1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy+1, cuz)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }
        if (worldIn.getBlockState(new BlockPos(cux+1, cuy+1, cuz+1)).getBlock() == Blocks.TORCH) {
            worldIn.spawnEntity(new PrimedIce(worldIn));
            logger.info("Torch! BOOM! ");
        }

    }
}
