package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import timicasto.quantumbase.creative.TabLoader;

public class WillowLeaves extends Block {
    public WillowLeaves() {
        super(Material.LEAVES);
        this.setRegistryName("willow_leaves");
        this.setUnlocalizedName("willow_leaves");
        this.setCreativeTab(TabLoader.envTab);
        assert I18n.format("tile.willow_leaves.name").equals("柳树树叶");

    }

}
