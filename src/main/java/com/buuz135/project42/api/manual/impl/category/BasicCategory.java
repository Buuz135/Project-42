package com.buuz135.project42.api.manual.impl.category;

import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.ICategoryDisplay;
import com.buuz135.project42.api.manual.ICategoryEntry;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCategory implements IBookCategory {

    private String name;
    private ICategoryDisplay display;
    private Map<ResourceLocation, ICategoryEntry> entryMap;

    public BasicCategory(String name, ICategoryDisplay display) {
        this.name = name;
        this.display = display;
        this.entryMap = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getTooltip() {
        return null;
    }

    @Override
    public ICategoryDisplay getDisplay() {
        return display;
    }

    @Override
    public Map<ResourceLocation, ICategoryEntry> getEntries() {
        return entryMap;
    }
}
