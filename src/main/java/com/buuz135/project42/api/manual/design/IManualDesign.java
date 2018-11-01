package com.buuz135.project42.api.manual.design;

public interface IManualDesign {

    IBackgroundDesign getBackgroundDesign();

    IBackgroundDesign getCategoryDesign();

    IBackgroundDesign getPageDesign();

    int getDisplayColor();

    int getTextColor();

    IDrawableTexture getPrevPageTexture();

    IDrawableTexture getNextPageTexture();

    IDrawableTexture getBackTexture();

}
