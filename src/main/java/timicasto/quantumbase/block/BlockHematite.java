package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockHematite extends Block {
    public BlockHematite() {
        super(Material.ROCK);
        setRegistryName("hematite");
        setUnlocalizedName("hematite");
        setCreativeTab(TabLoader.envTab);
        setHardness(2.0F);
        setHarvestLevel("Pickaxe", 2);
    }
}
