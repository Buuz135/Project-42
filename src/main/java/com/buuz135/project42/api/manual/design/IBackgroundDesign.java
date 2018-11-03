package com.buuz135.project42.api.manual.design;

public interface IBackgroundDesign extends IDrawableTexture {

    int getTopPadding();

    int getBottomPadding();

    int getLeftPadding();

    int getRightPadding();

    double getScale();

    IDrawableLocationTextureHovereable getPrevPageTexture();

    IDrawableLocationTextureHovereable getNextPageTexture();

    IDrawableLocationTextureHovereable getBackTexture();

}
