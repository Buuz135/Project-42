package com.buuz135.project42.api.manual;

import com.buuz135.project42.api.manual.design.display.IBookCategoryDisplay;
import com.google.common.collect.LinkedListMultimap;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IBookCategory {

    String getName();

    List<String> getTooltip();

    IBookCategoryDisplay getDisplay();

    LinkedListMultimap<ResourceLocation, ICategoryEntry> getEntries();

    default IBookCategory addEntry(ResourceLocation location, ICategoryEntry category) {
        getEntries().put(location, category);
        return this;
    }

}
