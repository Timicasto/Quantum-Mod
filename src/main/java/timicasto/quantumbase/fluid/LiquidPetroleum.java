package timicasto.quantumbase.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class LiquidPetroleum extends Fluid {
    public LiquidPetroleum() {
        super("petroleum", new ResourceLocation("quantumbase","blocks/petro_still"), new ResourceLocation("Quantumbase","blocks/petro_flow"));
    }
}
