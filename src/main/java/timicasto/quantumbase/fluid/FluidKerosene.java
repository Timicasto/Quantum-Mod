package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class FluidKerosene extends Fluid {
    public FluidKerosene() {
        super("kerosene", new ResourceLocation("quantumbase","blocks/kerosene_still"), new ResourceLocation("quantumbase","blocks/kerosene_flow"), new Color(230,220,191));
        setUnlocalizedName("kerosene");
        setDensity(3500);
        setViscosity(4000);
        setTemperature(350);
    }
}
