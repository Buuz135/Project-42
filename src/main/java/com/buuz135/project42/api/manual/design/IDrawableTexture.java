package com.buuz135.project42.api.manual.design;

import net.minecraft.util.ResourceLocation;

/**
 * Represents enough information to renderBack a texture inside of a GUI
 */
public interface IDrawableTexture {

    /**
     * The texture used to renderBack
     *
     * @return a resource location that points to the texture
     */
    ResourceLocation getTexture();

    /**
     * Where the texture is located in the Resource Location
     *
     * @return the X position of the texture
     */
    int getTextureX();

    /**
     * Where the texture is located in the Resource Location
     *
     * @return the Y position of the texture
     */
    int getTextureY();

    /**
     * The size of the texture inside of the Resource Location
     *
     * @return how wide the texture is
     */
    int getTextureWidth();

    /**
     * The size of the texture inside of the Resource Location
     *
     * @return how long the texture is
     */
    int getTextureHeight();
}
