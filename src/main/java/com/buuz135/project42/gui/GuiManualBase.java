package com.buuz135.project42.gui;

import com.buuz135.project42.Project42;
import com.buuz135.project42.gui.button.IHasTooltip;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiManualBase extends GuiScreen {

    public static final ResourceLocation BOOK_BACK = new ResourceLocation(Project42.MOD_ID, "textures/gui/book_back.png");

    private final ManualInfo manualInfo;
    private GuiScreen prevScreen;
    private int guiLeft;
    private int guiTop;
    private int guiXSize;
    private int guiYSize;

    public GuiManualBase(GuiScreen prevScreen, ManualInfo manualInfo) {
        this.prevScreen = prevScreen;
        this.manualInfo = manualInfo;
        float scale = 0.65f;
        this.guiXSize = (int) (256 * scale);
        this.guiYSize = (int) (312 * scale);
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
        this.mc.getTextureManager().bindTexture(BOOK_BACK);
        drawModalRectWithCustomSizedTexture(this.guiLeft, this.guiTop, 0, 0, this.guiXSize, this.guiYSize, this.guiXSize, this.guiYSize);
        drawCenteredString(Minecraft.getMinecraft().fontRenderer, new TextComponentTranslation(manualInfo.getAnnotation().displayName()).getFormattedText(), this.guiLeft + this.guiXSize / 2, this.guiTop - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4, manualInfo.getAnnotation().displayColor());
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
