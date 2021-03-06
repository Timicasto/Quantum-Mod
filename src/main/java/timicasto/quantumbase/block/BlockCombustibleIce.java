package timicasto.quantumbase.block;

import net.minecraft.block.BlockIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.register.QuantumBaseBlocks;

import java.util.Random;

public class BlockCombustibleIce extends BlockIce {
    private static Logger logger = LogManager.getLogger();
    private static final Random rnd = new Random();

    public BlockCombustibleIce() {
        super();
        this.setRegistryName("combustible_ice");
        this.setUnlocalizedName("combustible_ice");
        this.setCreativeTab(TabLoader.envTab);
        this.setHardness(3.0F);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(QuantumBaseBlocks.combustibleIceItemBlock,1);
    }
    
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        if (rnd.nextBoolean()) {
            if (!worldIn.isRemote) {
                PrimedIce primedIce = new PrimedIce(worldIn, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, null);
                worldIn.spawnEntity(primedIce);
            }
        }
    }
    
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            PrimedIce primedIce = new PrimedIce(worldIn, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, null);
            worldIn.spawnEntity(primedIce);
        }
    }
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            PrimedIce primedIce = new PrimedIce(worldIn, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, null);
            worldIn.spawnEntity(primedIce);
            return true;
        }
        else
        {
            return false;
        }
    }
}
