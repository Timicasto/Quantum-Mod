package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import timicasto.quantumbase.creative.TabLoader;

public class WillowWood extends Block {
    public WillowWood() {
        super(Material.WOOD);
        this.setRegistryName("willow_wood");
        this.setUnlocalizedName("willow_wood");
        this.setCreativeTab(TabLoader.envTab);
    }
}
