package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;

import java.util.*;

public abstract class StatesBlock<E extends Enum<E> & IEnumPropertyBlock> extends Block implements IStateBlock {
	protected static Property[] properties;

	public final String name;
	public final EnumProperty<E> property;
	public final Property[] additional;
	public final E[] values;
	boolean[] hasFlavor;
	protected Set<RenderType>[] stateTypes;
	protected Map<BlockState, Integer> opacities;
	protected Map<BlockState, Float> strength;
	protected boolean[] canBeDisassembled;
	protected boolean[] notNormalBlock;
	private boolean opaqueCube = false;

	public StatesBlock(String name, Material material, EnumProperty<E> property, Object... additional) {
		super(Properties.of(genMaterial(material, property, additional)));
		this.name = name;
		this.property = property;
		this.values = property.getValueClass().getEnumConstants();
		this.hasFlavor = new boolean[this.values.length];
		this.stateTypes = new Set[this.values.length];
		this.canBeDisassembled = new boolean[this.values.length];

		List<Property> properties = new ArrayList<>();
		for (Object o : additional) {
			if (o instanceof Property) {
				properties.add((Property)o);
			}
			if (o instanceof Property[]) {
				properties.addAll(Arrays.asList((Property[]) o));
			}
		}
		this.additional = properties.toArray(new Property[properties.size()]);
		this.registerDefaultState(defaultState());
		//this.setRegistryName(name);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Property property() {
		return null;
	}

	@Override
	public Enum[] enums() {
		return new Enum[0];
	}

	public E[] getValues() {
		return values;
	}

	@Override
	public BlockState state(int i) {
		BlockState state = this.stateDefinition.any().setValue(this.property, values[i]);
		return state;
	}

	public EnumProperty<E> getProperty() {
		return property;
	}

	@Override
	public boolean customMapper() {
		return false;
	}

	@Override
	public boolean appendProperty() {
		return true;
	}

	protected static Material genMaterial(Material material, EnumProperty<?> property, Object... additional) {
		List<Property> properties = new ArrayList<>();
		properties.add(property);
		for (Object o : additional) {
			if (o instanceof Property) {
				properties.add((Property) o);
			}
			if (o instanceof Property[]) {
				Collections.addAll(properties, (Property[]) o);
			}
		}
		StatesBlock.properties = properties.toArray(new Property[properties.size()]);
		return material;
	}

	protected BlockState defaultState() {
		BlockState state = this.stateDefinition.any().setValue(property, values[0]);
		for (Property value : additional) {
			if (value != null && !value.getPossibleValues().isEmpty()) {
				state = genState(state, value, value.getPossibleValues().iterator().next());
			}
		}
		return state;
	}

	@Override
	protected abstract void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

	protected static Object[] combine(Object[] curr, Object... tar) {
		Object[] arr = new Object[curr.length + tar.length];
		System.arraycopy(curr, 0, arr, 0, curr.length);
		System.arraycopy(tar, 0, arr, curr.length, tar.length);
		return arr;
	}

	@SuppressWarnings("unchecked")
	protected <P extends Comparable<P>> BlockState genState(BlockState in, Property<P> property, Object tar) {
		return in.setValue(property, (P)tar);
	}

}
