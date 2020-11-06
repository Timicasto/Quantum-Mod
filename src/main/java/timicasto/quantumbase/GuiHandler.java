package timicasto.quantumbase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import timicasto.quantumbase.gui.ContainerMetalSmelter;
import timicasto.quantumbase.gui.ContainerPetroleumProcessor;
import timicasto.quantumbase.gui.GuiContainerMetalSmelter;
import timicasto.quantumbase.gui.GuiContainerPetroleumProcessor;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

public class GuiHandler implements IGuiHandler {
    public static final int guiPetroleumProcessor = 0;
    public static final int guiMetalSmelter = 1;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            // case guiCoakOven:
            // return new ContainerCoakOven(player);
            case guiPetroleumProcessor:
                return new ContainerPetroleumProcessor(player.inventory, (TileEntityPetroleumProcessor) world.getTileEntity(new BlockPos(x, y, z)));
            case guiMetalSmelter:
                return new ContainerMetalSmelter();
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            // case guiCoakOven:
            //  return new GuiContainerCoakOven(new ContainerCoakOven(player));
            case guiPetroleumProcessor:
                return new GuiContainerPetroleumProcessor(player.inventory, (TileEntityPetroleumProcessor) world.getTileEntity(new BlockPos(x, y, z)));
            case guiMetalSmelter:
                return new GuiContainerMetalSmelter(player.inventoryContainer);
            default:
                return null;
        }

    }
}
