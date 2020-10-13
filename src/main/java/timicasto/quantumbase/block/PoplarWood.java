package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import timicasto.quantumbase.creative.TabLoader;

public class PoplarWood extends Block {
    public PoplarWood() {
        super(Material.WOOD);
        this.setRegistryName("poplar_wood");
        this.setUnlocalizedName("poplar_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
