package timicasto.quantumfarm.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timicasto.quantumfarm.creative.FarmTabLoader;

public class AppleWood extends Block {
    public AppleWood() {
        super(Material.WOOD);
        this.setRegistryName("apple_wood");
        this.setUnlocalizedName("apple_wood");
        this.setCreativeTab(FarmTabLoader.farmTab);
    }
}
