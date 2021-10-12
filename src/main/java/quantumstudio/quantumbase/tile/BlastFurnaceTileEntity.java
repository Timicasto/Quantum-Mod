package quantumstudio.quantumbase.tile;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quantumstudio.quantumbase.api.energy.EnergyWrapper;
import quantumstudio.quantumbase.gui.containers.BlastFurnaceMenu;
import quantumstudio.quantumbase.multiblock.MultiBlockBlast;
import quantumstudio.quantumbase.registries.Contents;
import quantumstudio.quantumbase.tile.templates.MultiBlockMachineTileEntity;
import quantumstudio.quantumbase.utils.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlastFurnaceTileEntity extends MultiBlockMachineTileEntity<BlastFurnaceTileEntity> implements MenuProvider {

	public List<ItemStack> slots = new ArrayList<>(3);
	public Container container = new SimpleContainer(3);

	public BlastFurnaceTileEntity() {
		super(Contents.BLAST_FURNACE, new int[]{3, 3, 3}, 0, false, MultiBlockBlast.instance);
		slots.addAll(Arrays.asList(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY));
	}

	@Override
	public void read(CompoundTag nbt, boolean desc) {
		super.read(nbt, desc);
		if (!desc) {
			slots = DataUtils.readMenu(nbt.getList("Slots", 10), 26);
		}
	}

	@Override
	public void write(CompoundTag nbt, boolean desc) {
		super.write(nbt, desc);
		if (!desc) {
			nbt.put("Slots",DataUtils.writeMenu(slots));
		}
	}

	public BlastFurnaceItemIntArray data = new BlastFurnaceItemIntArray();

	@Override
	public Component getDisplayName() {
		return new TextComponent(I18n.get("gui.title.blast"));
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory arg, Player arg2) {
		container = new SimpleContainer(slots.toArray(new ItemStack[]{}));
		return new BlastFurnaceMenu(i, arg, container, this, data);
	}

	@Override
	public int[] energyPos() {
		return new int[0];
	}

	@Override
	public int[] redstonePos() {
		return new int[0];
	}

	@Override
	public EnergyWrapper.EnergyWrapperImpl getWrappedCapability(Direction facing) {
		return null;
	}

	@Override
	public List<ItemStack> slots() {
		return slots;
	}

	@Override
	public int getMaxStackSize(int slot) {
		return 8;
	}

	@Override
	public void render(int slot) {

	}

	@Override
	public boolean canInsert(int slot, ItemStack tar) {
		return true;
	}

	@Override
	protected IFluidTank @NotNull [] getAccessibleFluidTanks(Direction side) {
		return new IFluidTank[0];
	}

	@Override
	protected boolean canFillFrom(int tank, Direction side, FluidStack resource) {
		return false;
	}

	@Override
	protected boolean canDrainFrom(int tank, Direction side) {
		return false;
	}

	@Override
	public void tick() {

	}

	@Override
	public float[] getBlockBound() {
		return new float[0];
	}

	ItemStackHandler ore = new ItemStackHandler(1);
	ItemStackHandler changer = new ItemStackHandler(1);
	ItemStackHandler addition = new ItemStackHandler(1);

	public ItemStackHandler getItemHandler(int index) {
		switch (index) {
			case 0:
				return ore;
			case 1:
				return changer;
			case 2:
				return addition;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			BlastFurnaceTileEntity trigger = trigger();
			if (trigger == null) {
				return LazyOptional.empty();
			}
			switch (side) {
				case UP:
					return LazyOptional.of(() -> (T)ore);
				case EAST:
					return LazyOptional.of(() -> (T)addition);
				case WEST:
					return LazyOptional.of(() -> (T)changer);
			}
		}
		return super.getCapability(cap, side);
	}

	public static class BlastFurnaceItemIntArray implements ContainerData {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;

		@Override
		public int get(int i) {
			switch (i) {
				case 0:
					return this.i1;
				case 1:
					return this.i2;
				case 2:
					return this.i3;
			}
			return 0;
		}

		@Override
		public void set(int i, int j) {
			switch (i) {
				case 0:
					this.i1 = j;
				case 1:
					this.i2 = j;
				case 2:
					this.i3 = j;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}
}
