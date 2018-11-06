package com.buuz135.project42.gui.button;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.gui.GuiPageEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

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
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            entry.getDisplay().render(mc, x, y, this.hovered);
            GlStateManager.popMatrix();
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
