package com.buuz135.project42.api.manual.impl.category;

import com.buuz135.project42.api.manual.ICategoryEntry;
import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;
import com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryEntryDisplay;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BasicCategoryEntry implements ICategoryEntry {

    private final ItemStack itemStack;

    public BasicCategoryEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public List<IContent> getContent() {
        return new ArrayList<>();
    }

    @Override
    public ICategoryEntryDisplay getDisplay() {
        return new ItemStackCategoryEntryDisplay(itemStack);
    }
}
