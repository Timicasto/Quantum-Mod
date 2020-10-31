package timicasto.quantumbase.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.item.ItemModBucket;
import timicasto.quantumbase.register.QuantumBaseBlocks;
import timicasto.quantumbase.register.QuantumBaseItems;
import timicasto.quantumbase.utils.EventHandler;

public class FluidLoader {
     public static Fluid SEA_WATER = new FluidSeaWater();
     public static Fluid PETROLEUM = new LiquidPetroleum();
     public static Fluid DIESEI = new FluidDieseiOil();
     public static Fluid GASOLINE = new FluidGasoline();
     public static Fluid KEROSENE = new FluidKerosene();
     private static Logger logger = LogManager.getLogger();

        public static void registerFluids() {
            registerFluid(SEA_WATER);
            registerFluid(PETROLEUM);
            registerFluid(DIESEI);
            registerFluid(GASOLINE);
            registerFluid(KEROSENE);
        }

        public static void registerFluid(Fluid fluid) {
            FluidRegistry.registerFluid(fluid);
            if (FluidRegistry.isFluidRegistered(fluid)) {
                logger.info(fluid + "IS ALREADY REGISTRIED");
            }
            if (FluidRegistry.addBucketForFluid(fluid)) {
                logger.info("ADDED Bucket for : " + fluid);
            } else {
                logger.error("ONE or MORE ERRORS OCCURRED DURING AddBucket for  " + fluid);
            }
        }
}
