package com.buuz135.project42.api.manual.design;

/**
 * A drawable that will always be drawn in the same coords relative to the GUI
 */
public interface IDrawableLocationTexture extends IDrawableTexture {

    int getPosX();

    int getPosY();
}
