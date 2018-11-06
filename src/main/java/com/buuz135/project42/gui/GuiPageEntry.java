/*
 * This file is part of Industrial Foregoing.
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

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.Page;
import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class GuiPageEntry extends GuiManualBase {

    private final CategoryEntry entry;
    private int page;

    public GuiPageEntry(GuiScreen prevScreen, ManualInfo manualInfo, CategoryEntry entry) {
        super(prevScreen, manualInfo);
        this.entry = entry;
        this.page = 0;
    }

    @Override
    public void drawScreenBack(int mouseX, int mouseY, float partialTicks) {
        super.drawScreenBack(mouseX, mouseY, partialTicks);
        if (entry.getPages().size() > page) {
            for (Page.FormattedContent formattedContent : entry.getPages().get(page).getFormattedContent()) {
                formattedContent.getContent().renderBack(Minecraft.getMinecraft(), formattedContent.getX() + this.getGuiLeft() + this.getBackground().getLeftPadding(), formattedContent.getY() + this.getGuiTop() + this.getBackground().getTopPadding() + 1, mouseX, mouseY);
            }
        }
        GlStateManager.color(1, 1, 1, 1);
    }

    @Override
    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        super.drawScreenFront(mouseX, mouseY, partialTicks);
        if (entry.getPages().size() > page) {
            for (Page.FormattedContent formattedContent : entry.getPages().get(page).getFormattedContent()) {
                formattedContent.getContent().renderFront(Minecraft.getMinecraft(), formattedContent.getX() + this.getGuiLeft() + this.getBackground().getLeftPadding(), formattedContent.getY() + this.getGuiTop() + this.getBackground().getTopPadding() + 1, mouseX, mouseY);
            }
        }
    }

    @Override
    public IBackgroundDesign getBackground() {
        return this.getManualInfo().getDesign().getPageDesign();
    }

    @Override
    public boolean hasNextButton() {
        return page < entry.getPages().size() - 1;
    }

    @Override
    public boolean hasPrevButton() {
        return page > 0;
    }

    @Override
    public void onNextButton() {
        ++page;
    }

    @Override
    public void onPrevButton() {
        --page;
    }
}
