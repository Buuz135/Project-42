package com.buuz135.project42.gui;

import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.ICategoryEntry;
import com.buuz135.project42.gui.button.CategoryEntryButton;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiCategoryEntryList extends GuiManualBase {

    private final IBookCategory category;
    private final int page;

    public GuiCategoryEntryList(GuiScreen prevScreen, ManualInfo manualInfo, IBookCategory category, int page) {
        super(prevScreen, manualInfo);
        this.category = category;
        this.page = page;
    }

    @Override
    public void drawScreenBack(int mouseX, int mouseY, float partialTicks) {
        super.drawScreenBack(mouseX, mouseY, partialTicks);
        this.mc.getTextureManager().bindTexture(this.getManualInfo().getDesign().getCategoryDesign().getTexture());
        drawTexturedModalRect(this.getGuiLeft(), this.getGuiTop(), this.getManualInfo().getDesign().getCategoryDesign().getTextureX(), this.getManualInfo().getDesign().getCategoryDesign().getTextureY(), this.getGuiXSize(), this.getGuiYSize());
    }

    @Override
    public void initGui() {
        super.initGui();
        int spaceX = this.getGuiXSize() - this.getManualInfo().getDesign().getCategoryDesign().getRightPadding() - this.getManualInfo().getDesign().getCategoryDesign().getLeftPadding();
        int spaceY = this.getGuiYSize() - this.getManualInfo().getDesign().getCategoryDesign().getTopPadding() - this.getManualInfo().getDesign().getCategoryDesign().getBottomPadding();
        int currentY = 0;
        int pointer = 0;
        while (currentY < spaceY) {
            int biggerY = 0;
            int currentX = 0;
            List<CategoryEntryButton> buttons = new ArrayList<>();
            while (currentX < spaceX) {
                if (pointer >= category.getEntries().values().size()) return;
                ICategoryEntry entry = category.getEntries().values().get(pointer);
                buttons.add(new CategoryEntryButton(pointer, this.getGuiLeft() + this.getManualInfo().getDesign().getCategoryDesign().getRightPadding() + currentX, this.getGuiTop() + this.getManualInfo().getDesign().getCategoryDesign().getTopPadding() + currentY, entry));
                currentX += entry.getDisplay().sizeX();
                if (entry.getDisplay().sizeY() > biggerY) biggerY = entry.getDisplay().sizeY();
                ++pointer;
            }
            currentY += biggerY;
            if (currentY < spaceY) {
                this.buttonList.addAll(buttons);
            }
        }
    }
}
