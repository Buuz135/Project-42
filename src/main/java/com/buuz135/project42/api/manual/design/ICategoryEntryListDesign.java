package com.buuz135.project42.api.manual.design;

import net.minecraft.util.ResourceLocation;

public interface ICategoryEntryListDesign extends IDrawableTexture {

    ResourceLocation getTexture();

    int getTopPadding();

    int getBottomPadding();

    int getLeftPadding();

    int getRightPadding();

    double getScale();
}
