package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.UniversalBucket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.register.QuantumBaseBlocks;
import timicasto.quantumbase.tile.TileEntityKeroseneLight;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockKeroseneLight extends Block {

    private UniversalBucket universalBucket = new UniversalBucket();
    private static Logger logger = LogManager.getLogger();

    public BlockKeroseneLight() {
        super(Material.GLASS);
        setCreativeTab(TabLoader.envTab);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(QuantumBaseBlocks.keroseneLight);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(QuantumBaseBlocks.keroseneLight);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityKeroseneLight();
    }


    public static void setLuminous(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.setBlockState(new BlockPos(x, y+1, z), QuantumBaseBlocks.glow.getDefaultState());
        logger.info("now luminous  ");
    }

    public static void setDLuminous(World world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        world.setBlockState(new BlockPos(x, y+1, z), Blocks.AIR.getDefaultState());
        logger.info("now disabled luminous");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityKeroseneLight && universalBucket.getFluid(playerIn.getHeldItem(hand)).getFluid() == FluidLoader.KEROSENE) {
            playerIn.setHeldItem(hand, ((TileEntityKeroseneLight)tile).tryAcceptFuel());
            playerIn.sendStatusMessage(new TextComponentString("Fuel: " + ((TileEntityKeroseneLight)tile).getFuel()), true);
        }
        ItemStack heldItem = playerIn.getHeldItem(hand);
        if (heldItem.getItem() == Items.FLINT_AND_STEEL) {
            if (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == Blocks.AIR) {

            } else {

            }
        }
        return true;
    }
}










