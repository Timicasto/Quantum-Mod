package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

public interface IToolboxInteractive {
	boolean interact(Direction facing, Player monitor, float x, float y, float z);
}
