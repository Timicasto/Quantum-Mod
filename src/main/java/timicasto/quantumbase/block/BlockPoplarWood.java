package timicasto.quantumbase.block;

import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;

public class BlockPoplarWood extends ModBlock {
    public BlockPoplarWood() {
        super(Material.WOOD, "poplar_wood");
        this.setUnlocalizedName("poplar_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
