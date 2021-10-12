package quantumstudio.quantumbase.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import icyllis.modernui.graphics.Canvas;
import icyllis.modernui.graphics.Paint;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import quantumstudio.quantumbase.gui.containers.BlastFurnaceMenu;

public class BlastFurnaceScreen extends AbstractContainerScreen<BlastFurnaceMenu> {
	public BlastFurnaceScreen(BlastFurnaceMenu menu, Inventory inv, Component title) {
		super(menu, inv, title);
	}

	@Override
	protected void renderBg(PoseStack arg, float f, int i, int j) {
		Canvas canvas = Canvas.getInstance();
		Paint paint = Paint.take();
		paint.reset();
		paint.setFeatherRadius(0.5F);
		paint.setStyle(Paint.Style.FILL);
		paint.setRGBA(16, 16, 16, 192);
		canvas.drawRoundRect(0, 0, width, height, 1, paint);
		paint.setStyle(Paint.Style.STROKE);
		paint.setRGBA(255, 255, 255, 255);
		canvas.drawRoundRect(0, 0, width, height, 1, paint);
		canvas.drawText("Mode", 20, 20);
		canvas.drawLine(30, 25, 40, 25);
	}

	@Override
	protected void init() {
		super.init();

	}
}
