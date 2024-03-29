package quantumstudio.quantumbase.registries;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import quantumstudio.quantumbase.multiblock.EnumMachines;

public class StateProperties {
	public static final DirectionProperty HORIZONTAL_FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
	public static final BooleanProperty SLAVE_MULTIBLOCK = BooleanProperty.create("mbslave");
	public static final BooleanProperty DYNAMIC_RENDER = BooleanProperty.create("dynamic_render");
	public static final BooleanProperty[] BOOLS = {
			BooleanProperty.create("boolx0"),
			BooleanProperty.create("boolx1"),
			BooleanProperty.create("boolx2")
	};
	public static final EnumProperty<EnumMachines> TYPES = EnumProperty.create("type", EnumMachines.class);
}
