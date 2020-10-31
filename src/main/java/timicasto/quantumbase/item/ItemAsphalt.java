package timicasto.quantumbase.item;

import net.minecraft.item.Item;
import timicasto.quantumbase.creative.TabLoader;

public class ItemAsphalt extends Item {
    public ItemAsphalt() {
        super();
        setRegistryName("asphalt");
        setUnlocalizedName("asphalt");
        setCreativeTab(TabLoader.envTab);
    }
}
