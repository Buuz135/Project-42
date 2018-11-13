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
package com.buuz135.project42.api.manual.impl.category.display;

import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.ManualHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class ItemStackCategoryEntryDisplay implements ICategoryEntryDisplay {

    private final ItemStack itemStack;
    private int sizeX;
    private int sizeY;

    public ItemStackCategoryEntryDisplay(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.sizeX = 122;
        this.sizeY = 17;
    }

    public ItemStackCategoryEntryDisplay(Item item) {
        this(new ItemStack(item));
    }

    public ItemStackCategoryEntryDisplay(Block block) {
        this(new ItemStack(block));
    }

    public ItemStackCategoryEntryDisplay(ItemStack itemStack, int sizeX, int sizeY) {
        this.itemStack = itemStack;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public ItemStackCategoryEntryDisplay(Item item, int sizeX, int sizeY) {
        this(new ItemStack(item), sizeX, sizeY);
    }

    public ItemStackCategoryEntryDisplay(Block block, int sizeX, int sizeY) {
        this(new ItemStack(block), sizeX, sizeY);
    }

    @Override
    public void render(Minecraft mc, int x, int y, boolean isHovered) {
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemIntoGUI(itemStack, x + 2, y + 2);
        ManualInfo info = ManualHelper.getCurrentManualInfoFromGUI();
        Color color = info == null ? Color.CYAN.darker() : new Color(info.getDesign().getTextColor());
        mc.fontRenderer.drawString(itemStack.getDisplayName(), x + 22, y + 7, isHovered ? color.darker().getRGB() : color.getRGB(), false);
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

}
