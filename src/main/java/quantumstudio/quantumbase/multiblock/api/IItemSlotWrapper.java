package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IItemSlotWrapper {
	List<ItemStack> slots();
	int getMaxStackSize(int slot);
	void render(int slot);
	boolean canInsert(int slot, ItemStack tar);

	default List<ItemStack> getDrops() {
		return slots();
	}
}