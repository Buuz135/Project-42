package com.buuz135.project42.gui;

import com.buuz135.project42.gui.button.IHasTooltip;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiManualBase extends GuiScreen {

    private final ManualInfo manualInfo;
    private GuiScreen prevScreen;
    private int guiLeft;
    private int guiTop;
    private int guiXSize;
    private int guiYSize;

    public GuiManualBase(GuiScreen prevScreen, ManualInfo manualInfo) {
        this.prevScreen = prevScreen;
        this.manualInfo = manualInfo;
        this.guiXSize = (int) (manualInfo.getDesign().getBackgroundDesign().getTextureWidth() * manualInfo.getDesign().getBackgroundDesign().getScale());
        this.guiYSize = (int) (manualInfo.getDesign().getBackgroundDesign().getTextureHeight() * manualInfo.getDesign().getBackgroundDesign().getScale());
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.guiXSize) / 2;
        this.guiTop = (this.height - this.guiYSize) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawScreenBack(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawScreenFront(mouseX, mouseY, partialTicks);
    }

    public void drawScreenBack(int mouseX, int mouseY, float partialTicks) {
        this.mc.getTextureManager().bindTexture(this.getManualInfo().getDesign().getBackgroundDesign().getTexture());
        drawTexturedModalRect(this.guiLeft, this.guiTop, this.getManualInfo().getDesign().getBackgroundDesign().getTextureX(), this.getManualInfo().getDesign().getBackgroundDesign().getTextureY(), this.guiXSize, this.guiYSize);
        drawCenteredString(Minecraft.getMinecraft().fontRenderer, new TextComponentTranslation(manualInfo.getDisplayName()).getFormattedText(), this.guiLeft + this.guiXSize / 2, this.guiTop - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4, manualInfo.getDesign().getDisplayColor());
        GlStateManager.color(1, 1, 1);
    }

    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        for (GuiButton button : this.buttonList) {
            if (button instanceof IHasTooltip && button.isMouseOver() && ((IHasTooltip) button).getTooltip() != null && !((IHasTooltip) button).getTooltip().isEmpty()) {
                drawHoveringText(((IHasTooltip) button).getTooltip(), mouseX, mouseY);
            }
        }
    }

    public ManualInfo getManualInfo() {
        return manualInfo;
    }

    public int getGuiLeft() {
        return guiLeft;
    }

    public int getGuiTop() {
        return guiTop;
    }

    public int getGuiXSize() {
        return guiXSize;
    }

    public int getGuiYSize() {
        return guiYSize;
    }
}
