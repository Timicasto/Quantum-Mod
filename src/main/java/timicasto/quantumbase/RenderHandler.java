package timicasto.quantumbase;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import timicasto.quantumbase.entity.PrimedIce;
import timicasto.quantumbase.entity.RenderPrimedIce;

public class RenderHandler {
    public static void RenderHandler() {

        net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(PrimedIce.class, new IRenderFactory<PrimedIce>() {
            @Override
            public Render<? super PrimedIce> createRenderFor(RenderManager manager) {
                return new RenderPrimedIce(manager);
            }
        });
    }
}
