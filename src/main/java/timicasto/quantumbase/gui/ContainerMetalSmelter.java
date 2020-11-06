package timicasto.quantumbase.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import timicasto.quantumbase.tile.TileEntityMetalSmelter;

public class ContainerMetalSmelter extends Container {
    private final TileEntityMetalSmelter tile;
    private int progress, fuel, reduceProgress;
    private IItemHandler sourceSlot;
    private IItemHandler fuelSlot;
    private IItemHandler resultSlot;

    public ContainerMetalSmelter(InventoryPlayer player, TileEntity tileEntity) {
        super();
        this.tile = (TileEntityMetalSmelter) tileEntity;
        IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(inventory, 0, 26, 11){
            @Override
            public int getSlotStackLimit() {
                return 8;
            }

            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Item.getItemFromBlock(Blocks.GOLD_ORE) || stack.getItem() == Item.getItemFromBlock(Blocks.IRON_ORE);
            }
        });

        this.addSlotToContainer(new SlotItemHandler(inventory, 1, 26, 59){
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem() == Items.COAL;
            }
        });

        this.addSlotToContainer(new SlotItemHandler(inventory, 2, 56, 59){
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
