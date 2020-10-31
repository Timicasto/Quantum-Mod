package timicasto.quantumbase.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.GuiHandler;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

import javax.annotation.Nullable;

public class BlockPetroleumProcessor extends BlockContainer {
    private final Logger logger = LogManager.getLogger();
    private UniversalBucket universalBucket = new UniversalBucket();
    public BlockPetroleumProcessor() {
        super(Material.IRON);
        setRegistryName("petroleum_processor");
        setUnlocalizedName("petroleum_processor");
        setCreativeTab(TabLoader.envTab);
        setHardness(2.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public static final AxisAlignedBB coakOven = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 3.0F);


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return coakOven;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPetroleumProcessor();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            int id = GuiHandler.guiPetroleumProcessor;
            if (playerIn.isSneaking()) {
                playerIn.openGui(QuantumBase.MODID, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
                logger.info("OPENED GUI : " + id);
            }
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityPetroleumProcessor && universalBucket.getFluid(playerIn.getHeldItem(hand)).getFluid() == FluidLoader.PETROLEUM) {
                playerIn.setHeldItem(hand, ((TileEntityPetroleumProcessor)tile).tryAcceptFuel());
                playerIn.sendStatusMessage(new TextComponentString("Fuel: " + ((TileEntityPetroleumProcessor)tile).getFuel()), true);
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

}
