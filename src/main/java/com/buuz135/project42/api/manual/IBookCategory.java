package com.buuz135.project42.api.manual;

import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface IBookCategory {

    String getName();

    List<String> getTooltip();

    ICategoryDisplay getDisplay();

    Map<ResourceLocation, ICategoryEntry> getEntries();

}
