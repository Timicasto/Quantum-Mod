package timicasto.quantumbase.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import timicasto.quantumbase.QuantumBase;
import timicasto.quantumbase.tile.TileEntityPetroleumProcessor;

public class GuiContainerPetroleumProcessor extends GuiContainer {
    private static final ResourceLocation BG = new ResourceLocation(QuantumBase.MODID + "textures/gui/petroleum_processor");
    private static final ResourceLocation BAR = new ResourceLocation(QuantumBase.MODID + "textures/gui/fluid_bar.png");
    private final InventoryPlayer player;
    private final TileEntityPetroleumProcessor tile;
    public GuiContainerPetroleumProcessor(InventoryPlayer player, TileEntityPetroleumProcessor tile) {
        super(new ContainerPetroleumProcessor(player, tile));
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
        String tileName = "石油精炼装置";
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 8, 4210752);
        FluidStack petroleumTank = tile.getPetroleumTank().getFluid();
        if (petroleumTank != null && petroleumTank.amount > 0) {
            int fluidColor = petroleumTank.getFluid().getColor(petroleumTank);
            float red = (fluidColor >> 16 & 0xFF) / 255F;
            float green = (fluidColor >> 8 & 0xFF) / 255F;
            float blue = (fluidColor & 0xFF) / 255F;

            float peraFilled = ((float) petroleumTank.amount) / ((float) tile.getPetroleumTank().getCapacity());
            peraFilled = MathHelper.clamp(peraFilled, 0F, 1F);
            int pxFilled = MathHelper.ceil(peraFilled * 61F);
            GlStateManager.color(red, green, blue, 1.0F);
            ResourceLocation rl = petroleumTank.getFluid().getStill(petroleumTank);
            TextureAtlasSprite tas = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(rl.toString());
            if (tas == null) {
                tas = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
            this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(15, 10 + 61 - pxFilled, tas, 20, pxFilled);
        }
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(BAR);
        this.drawTexturedModalRect(15, 10, 176, 0, 20, 61);
        FluidStack gasolineTank = tile.getGasolineTank().getFluid();
        if (gasolineTank != null && gasolineTank.amount > 0) {
            int fluidColor = gasolineTank.getFluid().getColor(gasolineTank);
            float red = (fluidColor >> 16 & 0xFF) / 255F;
            float green = (fluidColor >> 8 & 0xFF) / 255F;
            float blue = (fluidColor & 0xFF) / 255F;

            float peraFilled = ((float) gasolineTank.amount) / ((float) tile.getGasolineTank().getCapacity());
            peraFilled = MathHelper.clamp(peraFilled, 0F, 1F);
            int pxFilled = MathHelper.ceil(peraFilled * 61F);
            GlStateManager.color(red, green, blue, 1.0F);
            ResourceLocation rl = gasolineTank.getFluid().getStill(gasolineTank);
            TextureAtlasSprite tas = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(rl.toString());
            if (tas == null) {
                tas = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
            this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(45, 10 + 61 - pxFilled, tas, 20, pxFilled);
        }
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(BAR);
        this.drawTexturedModalRect(45, 10, 176, 0, 20, 61);
        FluidStack dieseiTank = tile.getDieseiOilTank().getFluid();
        if (dieseiTank != null && dieseiTank.amount > 0) {
            int fluidColor = dieseiTank.getFluid().getColor(dieseiTank);
            float red = (fluidColor >> 16 & 0xFF) / 255F;
            float green = (fluidColor >> 8 & 0xFF) / 255F;
            float blue = (fluidColor & 0xFF) / 255F;

            float peraFilled = ((float) dieseiTank.amount) / ((float) tile.getDieseiOilTank().getCapacity());
            peraFilled = MathHelper.clamp(peraFilled, 0F, 1F);
            int pxFilled = MathHelper.ceil(peraFilled * 61F);
            GlStateManager.color(red, green, blue, 1.0F);
            ResourceLocation rl = dieseiTank.getFluid().getStill(dieseiTank);
            TextureAtlasSprite tas = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(rl.toString());
            if (tas == null) {
                tas = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
            this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(75, 10 + 61 - pxFilled, tas, 20, pxFilled);
        }
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(BAR);
        this.drawTexturedModalRect(75, 10, 176, 0, 20, 61);
        FluidStack keroseneTank = tile.getDieseiOilTank().getFluid();
        if (keroseneTank != null && keroseneTank.amount > 0) {
            int fluidColor = keroseneTank.getFluid().getColor(keroseneTank);
            float red = (fluidColor >> 16 & 0xFF) / 255F;
            float green = (fluidColor >> 8 & 0xFF) / 255F;
            float blue = (fluidColor & 0xFF) / 255F;

            float peraFilled = ((float) keroseneTank.amount) / ((float) tile.getKeroseneTank().getCapacity());
            peraFilled = MathHelper.clamp(peraFilled, 0F, 1F);
            int pxFilled = MathHelper.ceil(peraFilled * 61F);
            GlStateManager.color(red, green, blue, 1.0F);
            ResourceLocation rl = keroseneTank.getFluid().getStill(keroseneTank);
            TextureAtlasSprite tas = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(rl.toString());
            if (tas == null) {
                tas = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }
            this.mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(105, 10 + 61 - pxFilled, tas, 20, pxFilled);
        }
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(BAR);
        this.drawTexturedModalRect(105, 10, 176, 0, 20, 61);
    }

}
