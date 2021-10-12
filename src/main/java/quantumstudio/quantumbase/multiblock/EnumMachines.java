package quantumstudio.quantumbase.multiblock;

import net.minecraft.util.StringRepresentable;
import quantumstudio.quantumbase.multiblock.api.IEnumPropertyBlock;

public enum EnumMachines implements StringRepresentable, IEnumPropertyBlock {

	BLAST_FURNACE(false),
	PLACEHOLDER(false);

	public boolean customState;

	EnumMachines(boolean customState) {
		this.customState = customState;
	}

	@Override
	public String getSerializedName() {
		return this.toString().toLowerCase();
	}


	@Override
	public boolean displayInTab() {
		return false;
	}

	public boolean isCustomState() {
		return customState;
	}

	public String getCustomState() {
		return getSerializedName().toLowerCase();
	}
}
