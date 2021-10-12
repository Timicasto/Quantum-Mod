package quantumstudio.quantumbase.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataUtils {

	public static List<ItemStack> readMenu(ListTag tag, int size) {
		List<ItemStack> ret = new ArrayList<>();
		int count = tag.size();
		for (int i = 0; i < count; i++) {
			CompoundTag temp = tag.getCompound(i);
			int slot = temp.getByte("Slot") & 255;
			if (slot < size) {
				ret.set(slot, ItemStack.of(temp));
			}
		}
		return ret;
	}

	public static ListTag writeMenu(ItemStack[] stacks) {
		ListTag ret = new ListTag();
		for (int i = 0; i < stacks.length; i++) {
			if (!stacks[i].isEmpty()) {
				CompoundTag tag = new CompoundTag();
				tag.putByte("Slot", (byte)i);
				tag = stacks[i].serializeNBT();
				ret.add(tag);
			}
		}
		return ret;
	}

	public static ListTag writeMenu(Collection<ItemStack> slots) {
		ListTag ret = new ListTag();
		byte slot = 0;
		for (ItemStack stack : slots) {
			if (!stack.isEmpty()) {
				CompoundTag tag = new CompoundTag();
				tag.putByte("Slot", slot);
				tag = stack.serializeNBT();
				ret.add(tag);
			}
			slot++;
		}
		return ret;
	}

}
