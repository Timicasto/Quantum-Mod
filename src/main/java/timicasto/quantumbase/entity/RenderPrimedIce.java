package timicasto.quantumbase.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPrimedIce extends RenderTNTPrimed {
    public RenderPrimedIce(RenderManager managerIn) {
        super(managerIn);
    }
}
