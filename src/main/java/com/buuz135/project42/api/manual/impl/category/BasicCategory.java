package com.buuz135.project42.api.manual.impl.category;

import com.buuz135.project42.api.manual.CategoryEntry;
import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.design.display.IBookCategoryDisplay;
import com.google.common.collect.LinkedListMultimap;
import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;

import java.util.List;

public class BasicCategory implements IBookCategory {

    private String name;
    private IBookCategoryDisplay display;
    private LinkedListMultimap<ResourceLocation, CategoryEntry> entryMap;
    private List<String> tooltip;

    public BasicCategory(String name, IBookCategoryDisplay display, String... tooltip) {
        this.name = name;
        this.display = display;
        this.entryMap = LinkedListMultimap.create();
        if (tooltip != null) {
            this.tooltip = Arrays.asList(tooltip);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getTooltip() {
        return tooltip;
    }

    @Override
    public IBookCategoryDisplay getDisplay() {
        return display;
    }

    @Override
    public LinkedListMultimap<ResourceLocation, CategoryEntry> getEntries() {
        return entryMap;
    }
}
