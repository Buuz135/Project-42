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
package com.buuz135.project42.gui.button;

import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.gui.GuiCategoryEntryList;
import com.buuz135.project42.gui.GuiManualBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryListButton extends GuiButton implements IHasTooltip {

    private final IBookCategory entry;

    public CategoryListButton(int buttonId, int x, int y, IBookCategory entry) {
        super(buttonId, x, y, "");
        this.entry = entry;
        this.height = entry.getDisplay().getSizeY();
        this.width = entry.getDisplay().getSizeX();
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            entry.getDisplay().render(mc, this.x, this.y, this.hovered);
            GlStateManager.popMatrix();
        }
    }

    @Nullable
    @Override
    public List<String> getTooltip() {
        List<String> tooltips = new ArrayList<>();
        if (entry.getTooltip() != null)
            tooltips.addAll(entry.getTooltip().stream().map(s -> new TextComponentTranslation(s).getFormattedText()).collect(Collectors.toList()));
        return tooltips;
    }

    public IBookCategory getEntry() {
        return entry;
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (isMouseOver() && !entry.getEntries().isEmpty()) {
            mc.displayGuiScreen(new GuiCategoryEntryList(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().currentScreen instanceof GuiManualBase ? ((GuiManualBase) Minecraft.getMinecraft().currentScreen).getManualInfo() : null, entry));
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }
}
