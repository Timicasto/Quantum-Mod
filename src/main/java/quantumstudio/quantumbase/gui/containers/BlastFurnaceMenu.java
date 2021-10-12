package quantumstudio.quantumbase.gui.containers;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import quantumstudio.quantumbase.registries.Contents;
import quantumstudio.quantumbase.tile.BlastFurnaceTileEntity;

public class BlastFurnaceMenu extends AbstractContainerMenu {
	private final BlastFurnaceTileEntity te;
	private BlastFurnaceTileEntity.BlastFurnaceItemIntArray data;

	//client
	public BlastFurnaceMenu(int containerId, Inventory inv, FriendlyByteBuf buf) {
		super(Contents.BLAST_FURNACE_MENU, containerId);
		BlockEntity t = inv.player.level.getBlockEntity(buf.readBlockPos());
		te = t instanceof BlastFurnaceTileEntity ? (BlastFurnaceTileEntity) t : null;
		Container container = new SimpleContainer(te.slots.toArray(new ItemStack[]{}));
		this.addSlot(new Slot(container, 0, 20, 32));
		this.addSlot(new Slot(container, 1, 60, 32));
		this.addSlot(new Slot(container, 2, 20, 60));
		layoutPlayerInventorySlots(inv, 8, 84);
	}

	//server
	public BlastFurnaceMenu(int containerId, Inventory inv, Container container, BlastFurnaceTileEntity tile, BlastFurnaceTileEntity.BlastFurnaceItemIntArray array) {
		super(Contents.BLAST_FURNACE_MENU, containerId);
		this.data = array;
		addDataSlots(array);
		te = tile;
		this.addSlot(new Slot(container, 0, 20, 32));
		this.addSlot(new Slot(container, 1, 60, 32));
		this.addSlot(new Slot(container, 2, 20, 60));
		layoutPlayerInventorySlots(inv, 8, 84);

	}

	// server-side logic
	@Override
	public boolean stillValid(Player player) {
		if (te != null) {
			BlockPos pos = te.getBlockPos();
			return player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) <= 64;
		}
		return false;
	}


	private int addSlotRange(Inventory inventory, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new Slot(inventory, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(Inventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(inventory, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	private void layoutPlayerInventorySlots(Inventory inventory, int leftCol, int topRow) {
		// NO NEED.
	}
}
