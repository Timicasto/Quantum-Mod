package quantumstudio.quantumbase.blocks;

import net.minecraft.util.StringRepresentable;

public enum EnumOres implements StringRepresentable {
	PYRITE(1, "pyrite"),
	CHALCOPYRITE(2, "chalcopyrite"),
	GALENA(3, "galena"),
	SPHALERITE(4, "sphalerite"),
	REALGAR(5, "realgar"),
	CARNALLITE(6, "carnallite"),
	MAGNETITE(7, "magnetite"),
	CALCITE(8, "calcite"),
	MALACHITE(9, "malachite"),
	PLASTER(10, "plaster"),
	BARITE(11, "barite"),
	APATITE(12, "apatite"),
	MICA(13, "mica"),
	FELDSPAR(14, "feldspar"),
	HORNBLENDE(15, "hornblende"),
	PYROXENE(16, "pyroxene"),
	OLIVINE(17, "olivine"),
	ANTIMONITE(18, "antimonite"),
	CINNABAR(19, "cinnabar"),
	CASSITERITE(20, "cassiterite"),
	CORUNDUM(21, "corundum"),
	QUMOITE(22, "qumoite");

	public int index;
	public String name;

	EnumOres(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return name;
	}
}
