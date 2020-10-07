package timicasto.quantumfarm.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FarmTab extends CreativeTabs {
    public FarmTab(){
        super("quantumfarm");
    }

    @Override
    public ItemStack getTabIconItem() {
        return null;
    }
}
