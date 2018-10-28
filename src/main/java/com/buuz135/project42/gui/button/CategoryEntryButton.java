package com.buuz135.project42.gui.button;

import com.buuz135.project42.api.manual.ICategoryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

public class CategoryEntryButton extends GuiButton {

    private final ICategoryEntry entry;

    public CategoryEntryButton(int buttonId, int x, int y, ICategoryEntry entry) {
        super(buttonId, x, y, "");
        this.entry = entry;
        this.width = entry.getDisplay().sizeX();
        this.height = entry.getDisplay().sizeY();
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            entry.getDisplay().render(mc, x, y, this.hovered);
            GlStateManager.popMatrix();
        }
    }
}
