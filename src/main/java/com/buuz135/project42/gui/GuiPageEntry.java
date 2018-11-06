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
