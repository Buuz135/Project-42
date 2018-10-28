package com.buuz135.project42.api.manual.impl.design;

import com.buuz135.project42.api.manual.design.ICategoryEntryListDesign;
import net.minecraft.util.ResourceLocation;


public class DefaultCategoryEntryListDesign implements ICategoryEntryListDesign {

    @Override
    public ResourceLocation getTexture() {
        return DefaultBackgroundDesign.BOOK_BACK;
    }

    @Override
    public int getTopPadding() {
        return 10;
    }

    @Override
    public int getBottomPadding() {
        return 14;
    }

    @Override
    public int getLeftPadding() {
        return 16;
    }

    @Override
    public int getRightPadding() {
        return 16;
    }

    @Override
    public double getScale() {
        return 1;
    }

    @Override
    public int getTextureX() {
        return 0;
    }

    @Override
    public int getTextureY() {
        return 0;
    }

    @Override
    public int getTextureWidth() {
        return 146;
    }

    @Override
    public int getTextureHeight() {
        return 180;
    }


}
