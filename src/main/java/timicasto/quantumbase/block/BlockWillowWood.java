package timicasto.quantumbase.block;

import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockWillowWood extends ModBlock {
    public BlockWillowWood() {
        super(Material.WOOD, "willow_wood");
        this.setUnlocalizedName("willow_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
