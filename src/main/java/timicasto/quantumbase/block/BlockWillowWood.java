package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockWillowWood extends Block {
    public BlockWillowWood() {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setRegistryName("willow_wood");
        this.setUnlocalizedName("willow_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
