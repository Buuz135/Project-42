/*
 * This file is part of Project 42.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.project42.gui;

import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.gui.button.CategoryListButton;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.List;

public class GuiCategoryList extends GuiManualBase {

    public GuiCategoryList(GuiScreen prevScreen, ManualInfo manualInfo) {
        super(prevScreen, manualInfo);
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
            List<CategoryListButton> buttons = new ArrayList<>();
            while (currentX < spaceX) {
                if (pointer >= this.getManualInfo().getCategories().size()) break;
                IBookCategory bookCategory = this.getManualInfo().getCategories().get(pointer);
                if (currentX + bookCategory.getDisplay().getSizeX() < spaceX) {
                    buttons.add(new CategoryListButton(pointer, this.getGuiLeft() + this.getManualInfo().getDesign().getCategoryEntryDesign().getRightPadding() + currentX, this.getGuiTop() + this.getManualInfo().getDesign().getCategoryEntryDesign().getTopPadding() + currentY + 16, bookCategory));
                    if (bookCategory.getDisplay().getSizeY() > biggerY) biggerY = bookCategory.getDisplay().getSizeY();
                    ++pointer;
                }
                currentX += bookCategory.getDisplay().getSizeX() + 1;
            }
            currentY += biggerY + 1;
            if (currentY < spaceY) {
                this.buttonList.addAll(buttons);
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
