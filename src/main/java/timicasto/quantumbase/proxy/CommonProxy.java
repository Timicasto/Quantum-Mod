package timicasto.quantumbase.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import timicasto.quantumbase.creative.TabLoader;

public class CommonProxy {

        public void preInit(FMLPreInitializationEvent event)
        {
                new TabLoader(event);
        }

        public void init(FMLInitializationEvent event)
        {

        }

        public void postInit(FMLPostInitializationEvent event)
        {

        }
}
