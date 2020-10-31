package timicasto.quantumbase.item;

import net.minecraft.item.Item;
import timicasto.quantumbase.creative.TabLoader;

public class ItemParaffin extends Item {
    public ItemParaffin() {
        super();
        this.setRegistryName("paraffin");
        this.setUnlocalizedName("paraffin");
        this.setCreativeTab(TabLoader.envTab);
    }
}
