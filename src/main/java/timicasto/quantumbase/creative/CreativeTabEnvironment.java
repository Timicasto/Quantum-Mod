package timicasto.quantumbase.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import timicasto.quantumbase.register.QuantumBaseBlocks;

public class CreativeTabEnvironment extends CreativeTabs {

    public CreativeTabEnvironment(){
        super("quantumbase_environment");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(QuantumBaseBlocks.willowWood);
    }
}
