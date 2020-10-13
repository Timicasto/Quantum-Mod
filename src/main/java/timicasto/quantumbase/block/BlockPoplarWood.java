package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockPoplarWood extends Block {
    public BlockPoplarWood() {
        super(Material.WOOD);
        this.setRegistryName("poplar_wood");
        this.setUnlocalizedName("poplar_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
