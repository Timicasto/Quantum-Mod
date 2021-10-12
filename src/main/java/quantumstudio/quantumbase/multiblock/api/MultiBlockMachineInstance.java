package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import quantumstudio.quantumbase.multiblock.EnumMachines;
import quantumstudio.quantumbase.registries.StateProperties;
import quantumstudio.quantumbase.tile.BlastFurnaceTileEntity;

public class MultiBlockMachineInstance extends MultiBlockPartBlock<EnumMachines> {
	public MultiBlockMachineInstance() {
		super("machine", Material.METAL, StateProperties.TYPES, StateProperties.DYNAMIC_RENDER, StateProperties.BOOLS[0]);
	}

	@Override
	public boolean customMapper() {
		return true;
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
		if (!world.isClientSide) {
			BlockEntity t = world.getBlockEntity(pos);
			switch (state.getValue(StateProperties.TYPES)) {
				case BLAST_FURNACE:
					NetworkHooks.openGui((ServerPlayer)player, (BlastFurnaceTileEntity)t, pos);
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Nullable
	@Override
	public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
		switch (state.getValue(StateProperties.TYPES)) {
			case BLAST_FURNACE:
				return new BlastFurnaceTileEntity();
		}
		return null;
	}

	//TODO review
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder builder) {
		Property[] properties = new Property[5];
		properties[0] = StateProperties.TYPES;
		properties[1] = StateProperties.DYNAMIC_RENDER;
		properties[2] = StateProperties.BOOLS[0];
		properties[3] = StateProperties.HORIZONTAL_FACING;
		properties[4] = StateProperties.SLAVE_MULTIBLOCK;
		builder.add(properties);
	}

}
