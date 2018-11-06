package com.buuz135.project42.api.manual.impl.category.display;

import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.ManualHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class ItemStackCategoryEntryDisplay implements ICategoryEntryDisplay {

    private final ItemStack itemStack;

    public ItemStackCategoryEntryDisplay(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void render(Minecraft mc, int x, int y, boolean isHovered) {
        mc.getRenderItem().renderItemIntoGUI(itemStack, x + 2, y + 2);
        ManualInfo info = ManualHelper.getCurrentManualInfoFromGUI();
        Color color = info == null ? Color.CYAN.darker() : new Color(info.getDesign().getTextColor());
        mc.fontRenderer.drawString(itemStack.getDisplayName(), x + 22, y + 7, isHovered ? color.darker().getRGB() : color.getRGB(), false);
    }

    @Override
    public int getSizeX() {
        return 123;
    }

    @Override
    public int getSizeY() {
        return 17;
    }

}
