package timicasto.quantumbase.creative;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TabLoader {
    public static CreativeTabs envTab;

    public TabLoader(FMLPreInitializationEvent event) {
        envTab = new CreativeTabEnvironment();
    }
}

