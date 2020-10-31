package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidDieseiOil extends Fluid {
    public FluidDieseiOil() {
        super("diesei", new ResourceLocation("quantumbase","blocks/diesei_still"), new ResourceLocation("quantumbase","blocks/diesei_flow"), new Color(170,97,4));
        setUnlocalizedName("diesei");
        setDensity(2400);
        setViscosity(3000);
        setTemperature(350);
    }
}
