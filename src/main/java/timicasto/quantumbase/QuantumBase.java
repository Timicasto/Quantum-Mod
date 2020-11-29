package timicasto.quantumbase;


import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.capability.CapabilityHandler;
import timicasto.quantumbase.environment.GenTree;
import timicasto.quantumbase.environment.PetroleumGenerator;
import timicasto.quantumbase.environment.WorldGenCustomStructure;
import timicasto.quantumbase.environment.WorldGenOres;
import timicasto.quantumbase.fluid.FluidLoader;
import timicasto.quantumbase.network.PacketMoisture;
import timicasto.quantumbase.proxy.CommonProxy;

@Mod(modid = QuantumBase.MODID, name = QuantumBase.NAME, version = QuantumBase.VERSION)
public class QuantumBase {
    public static final String MODID = "quantumbase";
    public static final String NAME = "Quantum Base";
    public static final String VERSION = "@version@";
    private static Logger logger = LogManager.getLogger();

    private SimpleNetworkWrapper networkWrapper;

    static {
        FluidRegistry.enableUniversalBucket();
        if (FluidRegistry.isUniversalBucketEnabled()) {
        logger.info("ENABLED UniversalBucket");
        } else {
            logger.warn("ENABLE UniversalBucket Failed");
        }
    }


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
        CapabilityHandler.setupCapabilities();
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        FluidLoader.registerFluids();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        GameRegistry.registerWorldGenerator(new GenTree(),3);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructure(),0);
        GameRegistry.registerWorldGenerator(new PetroleumGenerator(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
        networkWrapper.registerMessage(new PacketMoisture.Handler(), PacketMoisture.class, 1, Side.CLIENT);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }


    public static QuantumBase getInstance() {
        return instance;
    }

    public static SimpleNetworkWrapper getNetwork() {
        return instance.networkWrapper;
    }
}
