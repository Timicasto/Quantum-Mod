package timicasto.quantumbase.block;

import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockPoplarWood extends ModBlock {

    public BlockPoplarWood() {
        super(Material.WOOD);
        this.setRegistryName("poplar_wood");
        this.setUnlocalizedName("poplar_wood");
        this.setCreativeTab(TabLoader.envTab);
    }

    @Override
    public String name() {
        return "poplar_wood";
    }
}
