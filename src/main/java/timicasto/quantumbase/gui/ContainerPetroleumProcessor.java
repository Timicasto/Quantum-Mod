package timicasto.quantumbase.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

public class ContainerPetroleumProcessor extends Container {
    private TileEntityPetroleumProcessor tile;
    private int temperature, fuel;
    private IFluidHandler fluidHandler;
    private IItemHandler asphalt;
    private IItemHandler paraffin;

    public ContainerPetroleumProcessor(InventoryPlayer player, TileEntity tileEntity) {
        super();
        this.tile = (TileEntityPetroleumProcessor) tileEntity;
        IItemHandler asphaltSlot = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        IItemHandler paraffinSlot = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

        this.addSlotToContainer(new SlotItemHandler(asphaltSlot, 0, 26, 11){
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        this.addSlotToContainer(new SlotItemHandler(paraffinSlot, 0, 26, 59){
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        for (int i = 0 ; i < 9 ; i++) {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }

        for (int i = 0 ; i < 3 ; i++) {
            for (int k = 0 ; k < 9 ; k++) {
                this.addSlotToContainer(new Slot(player, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
