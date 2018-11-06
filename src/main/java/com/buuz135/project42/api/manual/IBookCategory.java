package com.buuz135.project42.api.manual;

import com.buuz135.project42.api.manual.design.display.IBookCategoryDisplay;
import com.google.common.collect.LinkedListMultimap;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * Represents a Book Category to be shown in the Category GUI
 * <p>
 * Example: {@link com.buuz135.project42.api.manual.impl.category.BasicCategory}
 */
public interface IBookCategory {

    /**
     * Gets the name of category
     * @return the name of the category
     */
    String getName();

    /**
     * Gets the tooltip strings to be rendered when hovered
     * @return
     */
    List<String> getTooltip();

    /**
     * Gets the display for the category
     * @return the display of the category
     */
    IBookCategoryDisplay getDisplay();

    /**
     * Gets all the categories entries with a resource location as an unique id
     * @return a map of resource locations an categories entries
     */
    LinkedListMultimap<ResourceLocation, CategoryEntry> getEntries();

    /**
     * Adds an entry to the map with the resource location as an unique id
     * @param location the unique id
     * @param category the category entry to add
     * @return itself
     */
    default IBookCategory addEntry(ResourceLocation location, CategoryEntry category) {
        getEntries().put(location, category);
        return this;
    }

}
