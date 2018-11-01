package com.buuz135.project42.gui;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
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
    }

    @Override
    public IBackgroundDesign getBackground() {
        return this.getManualInfo().getDesign().getCategoryEntryDesign();
    }

    @Override
    public void initGui() {
        super.initGui();
        int spaceX = this.getGuiXSize() - this.getManualInfo().getDesign().getCategoryEntryDesign().getRightPadding() - this.getManualInfo().getDesign().getCategoryEntryDesign().getLeftPadding();
        int spaceY = this.getGuiYSize() - this.getManualInfo().getDesign().getCategoryEntryDesign().getTopPadding() - this.getManualInfo().getDesign().getCategoryEntryDesign().getBottomPadding();
        int currentY = 0;
        int pointer = 0;
        while (currentY < spaceY) {
            int biggerY = 0;
            int currentX = 0;
            List<CategoryEntryButton> buttons = new ArrayList<>();
            while (currentX < spaceX) {
                if (pointer >= category.getEntries().values().size()) return;
                CategoryEntry entry = category.getEntries().values().get(pointer);
                buttons.add(new CategoryEntryButton(pointer, this.getGuiLeft() + this.getManualInfo().getDesign().getCategoryEntryDesign().getRightPadding() + currentX, this.getGuiTop() + this.getManualInfo().getDesign().getCategoryEntryDesign().getTopPadding() + currentY, entry));
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
