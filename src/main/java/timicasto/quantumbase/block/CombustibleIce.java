package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
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
import timicasto.quantumbase.ModItems;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.entity.PrimedIce;

import javax.annotation.Nullable;
import java.util.Random;

public class CombustibleIce extends Block {
    private static Logger logger = LogManager.getLogger();
    private EntityLivingBase igniter;

    public CombustibleIce() {
        super(Material.ICE);
        this.setRegistryName("combustible_ice");
        this.setUnlocalizedName("combustible_ice");
        this.setCreativeTab(TabLoader.envTab);
        this.setHardness(3.0F);
        assert I18n.format("tile.combustible_ice.name").equals("可燃冰");
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
        return new ItemStack(ModItems.combustibleIcecItemBlock,1);
    }

    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {

        boolean b = new Random().nextBoolean();
        if (b) {
            logger.info("blow");
        }
        if (!b) {
            logger.info("safe");
        }
        if (b) {
            if (!worldIn.isRemote) {
                PrimedIce primedIce = new PrimedIce(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
                worldIn.spawnEntity(primedIce);
                logger.info("PrimedIce summonded");
            }
        }
    }

    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            PrimedIce primedIce = new PrimedIce(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
            worldIn.spawnEntity(primedIce);
            logger.info("PrimedIce summonded");
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        ItemStack itemstack = playerIn.getHeldItem(hand);
        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE))
        {
            PrimedIce primedIce = new PrimedIce(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
            worldIn.spawnEntity(primedIce);
            logger.info("PrimedIce summonded");

            return true;
        }
        else
        {
            return false;
        }
    }
}
