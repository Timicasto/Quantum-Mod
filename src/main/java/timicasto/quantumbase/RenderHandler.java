package timicasto.quantumbase;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.entity.RenderPrimedIce;

public class RenderHandler {
    public static void RenderHandler() {
        RenderingRegistry.registerEntityRenderingHandler(PrimedIce.class, RenderPrimedIce::new);
    }
}
