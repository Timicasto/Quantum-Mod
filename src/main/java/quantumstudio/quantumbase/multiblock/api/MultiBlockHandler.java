package quantumstudio.quantumbase.multiblock.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockHandler {
	public static List<IMultiBlock> multi = new ArrayList<>();

	public static void register(IMultiBlock mb) {
		multi.add(mb);
	}

	public static List<IMultiBlock> getMultiBlocks() {
		return multi;
	}

	public interface IMultiBlock {
		String getUniqueName();
		boolean isTrigger(BlockState s);
		boolean form(Level level, BlockPos pos, Direction facing, Player player);
		ItemStack[][][] renderStructureInGUI();
		ItemStack[] materials();
		@OnlyIn(Dist.CLIENT)
		boolean overwriteBlockRender(ItemStack stack, int it);
		float getInGUIScale();
		@OnlyIn(Dist.CLIENT)
		boolean canRenderFormed();
		@OnlyIn(Dist.CLIENT)
		void renderFormed();
	}

	public static MultiBlockFormEvent postEvent(Player player, IMultiBlock mb, BlockPos pos, ItemStack toolbox) {
		MultiBlockFormEvent e = new MultiBlockFormEvent(player, mb, pos, toolbox);
		MinecraftForge.EVENT_BUS.post(e);
		return e;
	}

	@Cancelable
	public static class MultiBlockFormEvent extends PlayerEvent {
		public final IMultiBlock mb;
		public final BlockPos pos;
		public final ItemStack toolbox;

		public MultiBlockFormEvent(Player player, IMultiBlock mb, BlockPos pos, ItemStack toolbox) {
			super(player);
			this.mb = mb;
			this.pos = pos;
			this.toolbox = toolbox;
		}
	}
}
