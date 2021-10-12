package quantumstudio.quantumbase.tile.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quantumstudio.quantumbase.api.energy.IEnergyConnector;

public abstract class MultiBlockTemplateTileEntity extends BlockEntity {
	public MultiBlockTemplateTileEntity(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);
		this.read(nbt, false);
	}

	public abstract void read(CompoundTag nbt, boolean desc);

	@Override
	public CompoundTag serializeNBT() {
		super.serializeNBT();
		CompoundTag ret = new CompoundTag();
		this.write(ret, false);
		return ret;
	}

	public abstract void write(CompoundTag nbt, boolean desc);

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag tag = new CompoundTag();
		this.write(tag, true);
		return new ClientboundBlockEntityDataPacket(this.getBlockPos(), 3, tag);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.serializeNBT();
		write(tag, true);
		return tag;
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		read(pkt.getTag(), true);
	}

	public void receiveFromClient(CompoundTag msg) {

	}

	public void receiveFromServer(CompoundTag msg) {

	}

	public void onCollision(Level world, Entity entity) {

	}

	@Override
	public boolean triggerEvent(int id, int type) {
		if (id == 0 || id == 255) {
			markContainingUpdate(null);
			return true;
		} else if (id == 254) {
			BlockState state = level.getBlockState(getBlockPos());
			level.sendBlockUpdated(getBlockPos(), state, state, 3);
			return true;
		}
		return super.triggerEvent(id, type);
	}

	public void markContainingUpdate(BlockState state) {
		markUpdate(getBlockPos(), state);
	}

	public void markUpdate(BlockPos pos, BlockState state) {
		BlockState now = level.getBlockState(getBlockPos());
		if (state == null) {
			state = now;
		}
		level.sendBlockUpdated(pos, now, state, 3);
		level.updateNeighborsAt(pos, state.getBlock());
	}

	@NotNull
	@Override
	@SuppressWarnings("unchecked")
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction facing) {
		if (cap == CapabilityEnergy.ENERGY) {
			return LazyOptional.of(() -> (T)((IEnergyConnector)this).getWrappedCapability(facing));
		}
		return super.getCapability(cap, facing);
	}
}
