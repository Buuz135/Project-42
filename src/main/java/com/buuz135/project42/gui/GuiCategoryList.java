package com.buuz135.project42.gui;

import com.buuz135.project42.gui.button.CategoryListButton;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.gui.GuiScreen;

public class GuiCategoryList extends GuiManualBase {

    public GuiCategoryList(GuiScreen prevScreen, ManualInfo manualInfo) {
        super(prevScreen, manualInfo);
    }

    @Override
    public void initGui() {
        super.initGui();
        double scale = ((this.getGuiXSize() - 36D) / this.getManualInfo().getCategorySize().getLeft()) / 16D;
        for (int y = 0; y <= this.getManualInfo().getCategorySize().getRight(); y++) {
            for (int x = 0; x < this.getManualInfo().getCategorySize().getLeft(); x++) {
                int pos = y * this.getManualInfo().getCategorySize().getLeft() + x;
                if (this.getManualInfo().getCategories().size() > pos) {
                    this.addButton(new CategoryListButton(pos, this.getGuiLeft() + (int) (x * 17 * scale) + 18, this.getGuiTop() + (int) (y * 17 * scale) + 18, this.getManualInfo().getCategories().get(pos), scale));
                }
            }
        }
    }

    @Override
    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        super.drawScreenFront(mouseX, mouseY, partialTicks);


    }
}
