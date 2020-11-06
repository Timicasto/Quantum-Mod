package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.GuiHandler;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.tile.TileEntityKeroseneLight;
import timicasto.quantumbase.tile.TileEntityMetalSmelter;

public class BlockMetalSmelter extends Block {
    private Logger logger = LogManager.getLogger();
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            int id = GuiHandler.guiMetalSmelter;
            if (playerIn.isSneaking()) {
                playerIn.openGui(QuantumBase.MODID, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
                logger.info("OPENED GUI : " + id);
            }
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityMetalSmelter && playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
            playerIn.setHeldItem(hand, ((TileEntityMetalSmelter)tile).tryAcceptFuel());
            playerIn.sendStatusMessage(new TextComponentString("Fuel: " + ((TileEntityMetalSmelter)tile).getCO()), true);
        }
        return true;
    }
}