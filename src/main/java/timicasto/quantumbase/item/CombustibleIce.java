package timicasto.quantumbase.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.creative.TabLoader;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;

public class CombustibleIce extends ItemBanner {

    @ManualRegisterConstructor
    public CombustibleIce(){
        super();
        this.setRegistryName("combustible_ice_item");
        this.setUnlocalizedName("combustible_ice_item");
        this.setCreativeTab(TabLoader.envTab);
        I18n.format("item.combustible_ice_item.name");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 64000;
    }

}
