package timicasto.quantumbase.block;

import net.minecraft.block.material.Material;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;

public class BlockWillowWood extends ModBlock {
    public BlockWillowWood() {
        super(Material.WOOD);
        this.setRegistryName("willow_wood");
        this.setUnlocalizedName("willow_wood");
        this.setCreativeTab(TabLoader.envTab);
    }

    @Override
    public String name() {
        return "willow_wood";
    }
}
