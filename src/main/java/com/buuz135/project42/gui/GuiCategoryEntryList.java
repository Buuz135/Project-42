package com.buuz135.project42.gui;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.gui.button.CategoryEntryButton;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class GuiCategoryEntryList extends GuiManualBase {

    private final IBookCategory category;
    private List<GuiButton> addedButtons;
    private int pointer;
    private List<Integer> pageAmount;

    public GuiCategoryEntryList(GuiScreen prevScreen, ManualInfo manualInfo, IBookCategory category) {
        super(prevScreen, manualInfo);
        this.category = category;
        this.addedButtons = new ArrayList<>();
        this.pageAmount = new ArrayList<>();
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
        rebuild();
    }

    private void rebuild() {
        this.buttonList.removeIf(guiButton -> addedButtons.contains(guiButton));
        this.addedButtons.clear();
        int spaceX = this.getGuiXSize() - this.getManualInfo().getDesign().getCategoryEntryDesign().getRightPadding() - this.getManualInfo().getDesign().getCategoryEntryDesign().getLeftPadding();
        int spaceY = this.getGuiYSize() - this.getManualInfo().getDesign().getCategoryEntryDesign().getTopPadding() - this.getManualInfo().getDesign().getCategoryEntryDesign().getBottomPadding();
        int currentY = 0;
        int pointer = this.pointer;
        while (currentY < spaceY) {
            int biggerY = 0;
            int currentX = 0;
            List<CategoryEntryButton> buttons = new ArrayList<>();
            while (currentX < spaceX) {
                if (pointer >= category.getEntries().values().size()) return;
                CategoryEntry entry = category.getEntries().values().get(pointer);
                buttons.add(new CategoryEntryButton(pointer, this.getGuiLeft() + this.getManualInfo().getDesign().getCategoryEntryDesign().getRightPadding() + currentX, this.getGuiTop() + this.getManualInfo().getDesign().getCategoryEntryDesign().getTopPadding() + currentY, entry));
                currentX += entry.getDisplay().getSizeX();
                if (entry.getDisplay().getSizeY() > biggerY) biggerY = entry.getDisplay().getSizeY();
                ++pointer;
            }
            currentY += biggerY;
            if (currentY < spaceY) {
                this.addedButtons.addAll(buttons);
                this.buttonList.addAll(buttons);
            }
        }
    }

    @Override
    public boolean hasNextButton() {
        return this.pointer + addedButtons.size() < this.category.getEntries().size();
    }

    @Override
    public boolean hasPrevButton() {
        return this.pointer - addedButtons.size() > 0;
    }

    @Override
    public void onNextButton() {
        this.pointer += addedButtons.size();
        pageAmount.add(addedButtons.size());
        rebuild();
    }

    @Override
    public void onPrevButton() {
        this.pointer -= (pageAmount.get(pageAmount.size() - 1));
        rebuild();
    }
}
