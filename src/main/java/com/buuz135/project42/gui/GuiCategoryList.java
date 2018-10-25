package com.buuz135.project42.gui;

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
        double scale = ((this.getGuiXSize() - 40D) / this.getManualInfo().getCategorySize().getLeft()) / 16D;
        for (int y = 0; y <= this.getManualInfo().getCategorySize().getRight(); y++) {
            for (int x = 0; x < this.getManualInfo().getCategorySize().getLeft(); x++) {
                int pos = y * this.getManualInfo().getCategorySize().getLeft() + x;
                if (this.getManualInfo().getCategories().size() > pos) {
                    this.addButton(new CategoryListButton(pos, this.getGuiLeft() + (int) (x * 17 * scale) + 18, this.getGuiTop() + (int) (y * 17 * scale) + 30, this.getManualInfo().getCategories().get(pos), scale));
                }
            }
        }
    }

    @Override
    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        for (GuiButton button : this.buttonList) {
            if (button instanceof CategoryListButton) {
                if (button.isMouseOver()) {
                    GlStateManager.color(1, 1, 1);
                    String name = ((CategoryListButton) button).getEntry().getName();
                    Minecraft.getMinecraft().fontRenderer.drawString(new TextComponentTranslation(name).getFormattedText(), this.getGuiLeft() + this.getGuiXSize() / 2 - Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2, this.getGuiTop() + 14, this.getManualInfo().getAnnotation().textColor(), false);
                }
            }
        }
        super.drawScreenFront(mouseX, mouseY, partialTicks);
    }
}
