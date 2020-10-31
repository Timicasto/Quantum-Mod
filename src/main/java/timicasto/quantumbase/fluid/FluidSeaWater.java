package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidSeaWater extends Fluid {
    public FluidSeaWater() {
        super("sea_water", new ResourceLocation("quantumabase","fluids/seawater_still"), new ResourceLocation("quantumbase", "fluids/seawater_flow"), new Color(171,255,248));
        setUnlocalizedName("sea_water");
        setDensity(1100);
        setViscosity(800);
        setTemperature(350);
    }
}
