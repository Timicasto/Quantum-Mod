package quantumstudio.quantumbase;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import quantumstudio.quantumbase.registries.ClientRegistries;

@Mod(Constants.MODID)
public class QuantumBase {

	public QuantumBase() {
		MinecraftForge.EVENT_BUS.addListener(ClientRegistries.Client::clientSetup);
	}

}
