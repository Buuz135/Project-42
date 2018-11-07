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

import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.gui.button.DrawableButton;
import com.buuz135.project42.gui.button.IHasTooltip;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class GuiManualBase extends GuiScreen {

    private final ManualInfo manualInfo;
    private GuiScreen prevScreen;
    private int guiLeft;
    private int guiTop;
    private int guiXSize;
    private int guiYSize;

    private GuiButton prevPageButton;
    private GuiButton nextPageButton;
    private GuiButton backButton;

    public GuiManualBase(GuiScreen prevScreen, ManualInfo manualInfo) {
        this.prevScreen = prevScreen;
        this.manualInfo = manualInfo;
        this.guiXSize = getBackground().getTextureWidth();
        this.guiYSize = getBackground().getTextureHeight();
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.guiXSize) / 2;
        this.guiTop = (this.height - this.guiYSize) / 2;
        if (prevScreen != null) {
            this.addButton(backButton = new DrawableButton(-1, this.getGuiLeft(), this.getGuiTop(), this.getBackground().getBackTexture()) {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (this.isMouseOver()) mc.displayGuiScreen(prevScreen);
                    return super.mousePressed(mc, mouseX, mouseY);
                }
            });
        }
        if (this.nextPageButton != null && !this.buttonList.contains(nextPageButton)) this.addButton(nextPageButton);
        if (this.prevPageButton != null && !this.buttonList.contains(prevPageButton)) this.addButton(prevPageButton);
        this.getManualInfo().setLastManual(this);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawScreenBack(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawScreenFront(mouseX, mouseY, partialTicks);
    }

    public void drawScreenBack(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        drawCenteredString(Minecraft.getMinecraft().fontRenderer, new TextComponentTranslation(manualInfo.getDisplayName()).getFormattedText(), this.guiLeft + this.guiXSize / 2, this.guiTop - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4, manualInfo.getDesign().getDisplayColor());
        GlStateManager.color(1, 1, 1);
        IBackgroundDesign design = getBackground();
        this.mc.getTextureManager().bindTexture(design.getTexture());
        drawTexturedModalRect(this.getGuiLeft(), this.getGuiTop(), design.getTextureX(), design.getTextureY(), this.getGuiXSize(), this.getGuiYSize());
        if (hasNextButton() && nextPageButton == null) {
            this.addButton(nextPageButton = new DrawableButton(-2, this.getGuiLeft(), this.getGuiTop(), this.getBackground().getNextPageTexture()) {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (this.isMouseOver()) GuiManualBase.this.onNextButton();
                    return super.mousePressed(mc, mouseX, mouseY);
                }
            });
        }
        if (!hasNextButton() && nextPageButton != null) {
            this.buttonList.remove(nextPageButton);
            nextPageButton = null;
        }
        if (hasPrevButton() && prevPageButton == null) {
            this.addButton(prevPageButton = new DrawableButton(-3, this.getGuiLeft(), this.getGuiTop(), this.getBackground().getPrevPageTexture()) {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (this.isMouseOver()) GuiManualBase.this.onPrevButton();
                    return super.mousePressed(mc, mouseX, mouseY);
                }
            });
        }
        if (!hasPrevButton() && prevPageButton != null) {
            this.buttonList.remove(prevPageButton);
            prevPageButton = null;
        }
    }

    public void drawScreenFront(int mouseX, int mouseY, float partialTicks) {
        for (GuiButton button : this.buttonList) {
            if (button instanceof IHasTooltip && button.isMouseOver() && ((IHasTooltip) button).getTooltip() != null && !((IHasTooltip) button).getTooltip().isEmpty()) {
                drawHoveringText(((IHasTooltip) button).getTooltip(), mouseX, mouseY);
            }
        }
    }

    public ManualInfo getManualInfo() {
        return manualInfo;
    }

    public int getGuiLeft() {
        return guiLeft;
    }

    public int getGuiTop() {
        return guiTop;
    }

    public int getGuiXSize() {
        return guiXSize;
    }

    public int getGuiYSize() {
        return guiYSize;
    }

    public boolean hasNextButton() {
        return false;
    }

    public boolean hasPrevButton() {
        return false;
    }

    public void onNextButton() {

    }

    public void onPrevButton() {

    }

    public abstract IBackgroundDesign getBackground();


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
