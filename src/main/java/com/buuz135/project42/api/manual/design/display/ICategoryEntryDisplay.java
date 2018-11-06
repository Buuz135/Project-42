package com.buuz135.project42.api.manual.design.display;

import net.minecraft.client.Minecraft;

/**
 * Used to display all the entries in a category, where the getSize methods are used to fit as many possible entries inside the manual page.
 * <p>
 * Example: {@link com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryEntryDisplay}
 */
public interface ICategoryEntryDisplay {

    void render(Minecraft mc, int x, int y, boolean isHovered);

    int getSizeX();

    int getSizeY();

}
