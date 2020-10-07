package timicasto.quantumfarm.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FarmTabLoader {
    public static CreativeTabs farmTab;

    public FarmTabLoader(FMLPreInitializationEvent event) {
        farmTab = new FarmTab();
    }
}
