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

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.gui.GuiPageEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class CategoryEntryButton extends GuiButton {

    private final CategoryEntry entry;

    public CategoryEntryButton(int buttonId, int x, int y, CategoryEntry entry) {
        super(buttonId, x, y, "");
        this.entry = entry;
        this.width = entry.getDisplay().getSizeX();
        this.height = entry.getDisplay().getSizeY();
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            entry.getDisplay().render(mc, x, y, this.hovered);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (isMouseOver() && !entry.getPages().isEmpty()) {
            mc.displayGuiScreen(new GuiPageEntry(mc.currentScreen, Minecraft.getMinecraft().currentScreen instanceof GuiManualBase ? ((GuiManualBase) Minecraft.getMinecraft().currentScreen).getManualInfo() : null, entry));
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }
}
