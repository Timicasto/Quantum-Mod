package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import java.awt.*;

public class LiquidPetroleum extends Fluid {
    public LiquidPetroleum() {
        super("petroleum", new ResourceLocation("quantumbase","blocks/petro_still"), new ResourceLocation("quantumbase","blocks/petro_flow"), new Color(44,37,37));
        setUnlocalizedName("petroleum");
        setDensity(4000);
        setViscosity(5000);
        setTemperature(350);
    }

}
