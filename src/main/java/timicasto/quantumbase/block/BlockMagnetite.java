package timicasto.quantumbase.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;

public class BlockMagnetite extends Block {
    public BlockMagnetite() {
        super(Material.ROCK);
        setRegistryName("magnetite");
        setUnlocalizedName("magnetite");
        setCreativeTab(TabLoader.envTab);
    }
}
