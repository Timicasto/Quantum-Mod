package timicasto.quantumfarm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import timicasto.quantumbase.environment.GenTree;
import timicasto.quantumbase.environment.WorldGenCustomStructure;
import timicasto.quantumbase.proxy.CommonProxy;
import timicasto.quantumfarm.proxy.FarmCommonProxy;
import timicasto.quantumfarm.world.FarmGenTree;

@Mod(modid = QuantumFarm.MODID, name = QuantumFarm.NAME, version = QuantumFarm.VERSION)
public class QuantumFarm {
    public static final String MODID = "quantumfarm";
    public static final String NAME = "Quantum Farm";
    public static final String VERSION = "1.0.1";
    private static QuantumFarm instance;

    @SidedProxy(clientSide = "timicasto.quantumfarm.proxy.FarmClientProxy", serverSide = "timicasto.quantumfarm.proxy.FarmCommonProxy")
    public static FarmCommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        proxy.preInit(event);
        GameRegistry.registerWorldGenerator(new FarmGenTree(),0);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }


    public static QuantumFarm getInstance() {
        return instance;
    }

}
