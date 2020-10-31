package timicasto.quantumbase.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(IMoisture.class)
    public static Capability<IMoisture> capMoisture;

    public static void setupCapabilities(){
        CapabilityManager.INSTANCE.register(IMoisture.class, new CapabilityMoisture.Storage(), Implementation.class);
    }
}
