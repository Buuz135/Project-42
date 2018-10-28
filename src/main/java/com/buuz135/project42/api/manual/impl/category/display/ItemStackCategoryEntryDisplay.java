package com.buuz135.project42.api.manual.impl.category.display;

import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
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
        mc.fontRenderer.drawString(itemStack.getDisplayName(), x + 22, y + 7, isHovered ? new Color(getColor()).darker().getRGB() : getColor(), false);
    }

    @Override
    public int sizeX() {
        return 115;
    }

    @Override
    public int sizeY() {
        return 17;
    }

    @Override
    public int getColor() {
        return Color.CYAN.darker().getRGB();
    }
}
