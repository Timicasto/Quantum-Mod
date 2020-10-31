package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidGasoline extends Fluid {
    public FluidGasoline() {
        super("gasoline", new ResourceLocation("quantumbase","blocks/gasoline_still"), new ResourceLocation("quantumbase","blocks/gasoline_flow"), new Color(224, 202, 132));
        setUnlocalizedName("gasoline");
        setDensity(1600);
        setViscosity(2400);
        setTemperature(350);
    }
}
