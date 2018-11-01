package com.buuz135.project42.api.manual.impl.category;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryEntryDisplay;
import net.minecraft.item.ItemStack;

public class BasicCategoryEntry extends CategoryEntry {

    private final ItemStack itemStack;

    public BasicCategoryEntry(ItemStack itemStack) {
        super();
        this.itemStack = itemStack;
    }

    public ICategoryEntryDisplay getDisplay() {
        return new ItemStackCategoryEntryDisplay(itemStack);
    }

}
