package timicasto.quantumbase.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.creative.TabLoader;

public class ItemCombustibleIce extends Item {
    public ItemCombustibleIce(){
        super();
        this.setRegistryName("combustible_ice_item");
        this.setUnlocalizedName("combustible_ice_item");
        this.setCreativeTab(TabLoader.envTab);
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 64000;
    }

}
