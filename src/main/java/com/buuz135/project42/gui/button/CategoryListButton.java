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
    private double buttonScale;

    public CategoryListButton(int buttonId, int x, int y, IBookCategory entry, double buttonScale) {
        super(buttonId, x, y, "");
        this.entry = entry;
        this.buttonScale = buttonScale;
        this.height = (int) (16 * buttonScale);
        this.width = (int) (16 * buttonScale);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.scale(buttonScale, buttonScale, buttonScale);
            entry.getDisplay().render(mc, (int) (this.x / buttonScale), (int) (this.y / buttonScale), this.hovered);
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
        if (isMouseOver()) {
            mc.displayGuiScreen(new GuiCategoryEntryList(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().currentScreen instanceof GuiManualBase ? ((GuiManualBase) Minecraft.getMinecraft().currentScreen).getManualInfo() : null, entry, 0));
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }
}
