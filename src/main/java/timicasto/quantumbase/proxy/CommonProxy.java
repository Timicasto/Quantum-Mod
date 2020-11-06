package timicasto.quantumbase.proxy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import timicasto.quantumbase.CraftingHandler;
import timicasto.quantumbase.GuiHandler;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.creative.TabLoader;

import java.lang.reflect.Field;

public class CommonProxy {

        public void preInit(FMLPreInitializationEvent event)
        {
                new TabLoader(event);
        }

        public void init(FMLInitializationEvent event)
        {
                NetworkRegistry.INSTANCE.registerGuiHandler(QuantumBase.instance, new GuiHandler());
                new CraftingHandler().removeSmeltingRecipe();
        }

        public void postInit(FMLPostInitializationEvent event)
        {
        }
}
