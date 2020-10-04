package timicasto.quantumbase;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.proxy.CommonProxy;

@Mod(modid = QuantumBase.MODID, name = QuantumBase.NAME, version = QuantumBase.VERSION)
public class QuantumBase {
    public static final String MODID = "quantumbase";
    public static final String NAME = "Quantum Base";
    public static final String VERSION = "1.0.1";

    private static Logger logger;

    @SidedProxy(clientSide = "timicasto.quantumbase.proxy.ClientProxy",serverSide = "timicasto.quantumbase.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Initializing Quantum Base");
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
