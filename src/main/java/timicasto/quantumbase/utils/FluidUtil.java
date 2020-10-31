package timicasto.quantumbase.utils;

import net.minecraftforge.fluids.Fluid;

public class FluidUtil {
    public static boolean fluidsSame(Fluid f1, Fluid f2, boolean fuzzy)
    {
        if (f1 == null || f2 == null || f1.getName() == null)
        {
            return false;
        }
        if (fuzzy)
        {
            if (f1.getName().startsWith("petroleum"))
            {
                return f2.getName().startsWith("petroleum");
            }
            if (f1.getName().startsWith("water"))
            {
                return f2.getName().startsWith("water");
            }
        }
        return f1.getName().equals(f2.getName());
    }

    public static boolean fluidsSame(Fluid f1, String name2, boolean fuzzy)
    {
        if (f1 == null || name2 == null || f1.getName() == null)
        {
            return false;
        }
        if (fuzzy)
        {
            return f1.getName().startsWith(name2);
        }
        return f1.getName().equals(name2);
    }

}
