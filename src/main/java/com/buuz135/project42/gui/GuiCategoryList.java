package com.buuz135.project42.gui;

import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.gui.button.CategoryListButton;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiCategoryList extends GuiManualBase {

    public GuiCategoryList(GuiScreen prevScreen, ManualInfo manualInfo) {
        super(prevScreen, manualInfo);
    }

    @Override
    public void initGui() {
        super.initGui();
        double scale = ((this.getGuiXSize() - this.getManualInfo().getDesign().getCategoryDesign().getLeftPadding()
                - this.getManualInfo().getDesign().getCategoryDesign().getRightPadding() - 2) / (double) this.getManualInfo().getCategoryXSize()) / 16D;
        for (int y = 0; y <= this.getManualInfo().getCategoryXSize(); y++) {
            for (int x = 0; x < this.getManualInfo().getCategoryXSize(); x++) {
                int pos = y * this.getManualInfo().getCategoryXSize() + x;
                if (this.getManualInfo().getCategories().size() > pos) {
                    this.addButton(new CategoryListButton(pos, 1 + this.getGuiLeft() + (int) (x * 16 * scale) + this.getManualInfo().getDesign().getCategoryDesign().getLeftPadding(),
                            this.getGuiTop() + (int) (y * 17 * scale) + this.getManualInfo().getDesign().getCategoryDesign().getTopPadding() + 16,
                            this.getManualInfo().getCategories().get(pos), scale));
                }
            }
        }
    }

    @Override
    public void drawScreenBack(int mouseX, int mouseY, float partialTicks) {
        super.drawScreenBack(mouseX, mouseY, partialTicks);
    }

    @Override
    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        for (GuiButton button : this.buttonList) {
            if (button instanceof CategoryListButton) {
                if (button.isMouseOver()) {
                    GlStateManager.color(1, 1, 1);
                    String name = ((CategoryListButton) button).getEntry().getName();
                    Minecraft.getMinecraft().fontRenderer.drawString(new TextComponentTranslation(name).getFormattedText(),
                            this.getManualInfo().getDesign().getCategoryDesign().getLeftPadding()
                                    + this.getGuiLeft() + (this.getGuiXSize() - this.getManualInfo().getDesign().getCategoryDesign().getLeftPadding()
                                    - this.getManualInfo().getDesign().getCategoryDesign().getRightPadding()) / 2 - Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2,
                            this.getGuiTop() + this.getManualInfo().getDesign().getCategoryDesign().getTopPadding() + 4,
                            this.getManualInfo().getDesign().getTextColor(), false);
                }
            }
        }
        super.drawScreenFront(mouseX, mouseY, partialTicks);
    }

    @Override
    public IBackgroundDesign getBackground() {
        return this.getManualInfo().getDesign().getCategoryDesign();
    }
}
