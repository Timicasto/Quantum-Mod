package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import quantumstudio.quantumbase.registries.StateProperties;

public abstract class MultiBlockPartBlock<E extends Enum<E> & IEnumPropertyBlock> extends ContainerBlock<E> {
	public MultiBlockPartBlock(String name, Material material, EnumProperty<E> property, Object... additional) {
		super(name, material, property, combine(additional, StateProperties.HORIZONTAL_FACING, StateProperties.SLAVE_MULTIBLOCK));
	}
}
