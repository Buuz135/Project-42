package com.buuz135.project42.api.manual.design.display;

import net.minecraft.client.Minecraft;

/**
 * Used to renderBack an icon in the category page, rendered in 16x16 scaled automatically by Project42
 * <p>
 * Example {@link com.buuz135.project42.api.manual.impl.category.display.ItemStackCategoryDisplay}
 */
public interface IBookCategoryDisplay {

    void render(Minecraft mc, int x, int y, boolean isHovered);

}
