package timicasto.quantumbase.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.creative.TabLoader;

public class CombustibleIce extends Item {
    public CombustibleIce(){
        super();
        this.setRegistryName("combustible_ice_item");
        this.setUnlocalizedName("combustible_ice_item");
        this.setCreativeTab(TabLoader.envTab);
        assert I18n.format("item.combustible_ice_item.name").equals("可燃冰");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 64000;
    }

}
