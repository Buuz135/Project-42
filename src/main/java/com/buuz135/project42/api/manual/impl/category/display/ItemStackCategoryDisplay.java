package com.buuz135.project42.api.manual.impl.category.display;

import com.buuz135.project42.api.manual.design.display.IBookCategoryDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class ItemStackCategoryDisplay implements IBookCategoryDisplay {

    private final ItemStack display;

    public ItemStackCategoryDisplay(ItemStack display) {
        this.display = display;
    }

    @Override
    public void render(Minecraft mc, int x, int y, boolean isHovered) {
        mc.getRenderItem().renderItemIntoGUI(display, x, y);
    }
}
