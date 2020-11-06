package timicasto.quantumbase.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.tile.TileEntityMetalSmelter;

public class GuiContainerMetalSmelter extends GuiContainer {
    private static final ResourceLocation BG = new ResourceLocation(QuantumBase.MODID + ":textures/gui/metalsmelter_bg.png");
    private final InventoryPlayer player;
    private final TileEntityMetalSmelter tile;

    public GuiContainerMetalSmelter(InventoryPlayer player, TileEntityMetalSmelter tile) {
        super(new ContainerMetalSmelter(player, tile));
        this.player = player;
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BG);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        String title = "金属熔炼炉";
        this.fontRenderer.drawString(title, (this.xSize / 2 - this.fontRenderer.getStringWidth(title) / 2) + 3, 8, 4210752);
        FluidStack carbonMonoxide = tile.getCarbonMonoxide().getFluid();
        if (carbonMonoxide != null && carbonMonoxide.amount > 0) {
            int fluidColor = carbonMonoxide.getFluid().getColor(carbonMonoxide);
            float red = (fluidColor >> 16 & 0xFF) / 255F;
            float green = (fluidColor >> 8 & 0xFF) / 255F;
            float blue = (fluidColor & 0xFF) / 255F;

            float peraFilled = ((float) carbonMonoxide.amount) / ((float) tile.getCarbonMonoxide().getCapacity());
            peraFilled = MathHelper.clamp(peraFilled, 0F, 1F);
            int pxFilled = MathHelper.ceil(peraFilled * 61F);
            GlStateManager.color(red, green, blue, 1.0F);
            ResourceLocation rl = carbonMonoxide.getFluid().getStill(carbonMonoxide);
            TextureAtlasSprite tas = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(rl.toString());
            if (tas == null) {
                tas = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
            this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(15, 10 + 61 - pxFilled, tas, 20, pxFilled);
        }
    }
}
