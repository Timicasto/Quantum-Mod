package timicasto.quantumbase;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.environment.GenTree;
// import timicasto.quantumbase.fluid.LiquidPetroleum;
import timicasto.quantumbase.environment.WorldGenCustomStructure;
import timicasto.quantumbase.proxy.CommonProxy;

@Mod(modid = QuantumBase.MODID, name = QuantumBase.NAME, version = QuantumBase.VERSION)
public class QuantumBase {
    public static final String MODID = "quantumbase";
    public static final String NAME = "Quantum Base";
    public static final String VERSION = "1.0.2";
    private Logger logger;


    @Mod.Instance(QuantumBase.MODID)
    public static QuantumBase instance;

    // public static final Fluid petroleum = new LiquidPetroleum();

    @SidedProxy(clientSide = "timicasto.quantumbase.proxy.ClientProxy",serverSide = "timicasto.quantumbase.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //所有变量初始化必须在最开始，不然小心NPE
        instance = this;
        proxy.preInit(event);
        logger = event.getModLog();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        // FluidRegistry.registerFluid(petroleum);
        // FluidRegistry.addBucketForFluid(petroleum);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        GameRegistry.registerWorldGenerator(new GenTree(),3);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructure(),0);
    }


    public static QuantumBase getInstance() {
        return instance;
    }
}
