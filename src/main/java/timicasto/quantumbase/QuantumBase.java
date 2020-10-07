package timicasto.quantumbase;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import timicasto.quantumbase.creative.EnvCreativeTab;
import timicasto.quantumbase.environment.GenTree;
// import timicasto.quantumbase.fluid.LiquidPetroleum;
import timicasto.quantumbase.environment.WorldGenCustomStructure;
import timicasto.quantumbase.proxy.CommonProxy;

@Mod(modid = QuantumBase.MODID, name = QuantumBase.NAME, version = QuantumBase.VERSION)
public class QuantumBase {
    public static final String MODID = "quantumbase";
    public static final String NAME = "Quantum Base";
    public static final String VERSION = "1.0.1";
    private static QuantumBase instance;

    // public static final Fluid petroleum = new LiquidPetroleum();

    @SidedProxy(clientSide = "timicasto.quantumbase.proxy.ClientProxy",serverSide = "timicasto.quantumbase.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //所有变量初始化必须在最开始，不然小心NPE
        instance = this;
        proxy.preInit(event);
        GameRegistry.registerWorldGenerator(new GenTree(),3);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStructure(),2);
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
    }


    public static QuantumBase getInstance() {
        return instance;
    }
}
