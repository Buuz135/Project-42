package com.buuz135.project42.api.manual.design;

public interface IBackgroundDesign extends IDrawableTexture {

    int getTopPadding();

    int getBottomPadding();

    int getLeftPadding();

    int getRightPadding();

    double getScale();

    IDrawableLocationTexture getPrevPageTexture();

    IDrawableLocationTexture getNextPageTexture();

    IDrawableLocationTexture getBackTexture();

}
