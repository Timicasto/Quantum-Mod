package timicasto.quantumbase.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.ModItems;

public class EnvCreativeTab extends CreativeTabs {

    public EnvCreativeTab(){
        super("quantumbase_environment");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.willowWood);
    }
}
