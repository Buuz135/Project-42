package com.buuz135.project42.api.manual.design;

/**
 * Represent the information to renderBack a manual background
 * <p>
 * Example: {@link com.buuz135.project42.api.manual.impl.design.DefaultBackgroundDesign}
 */
public interface IBackgroundDesign extends IDrawableTexture {

    /**
     * How much padding on the top the texture has
     *
     * @return the amount of pixels of top padding
     */
    int getTopPadding();

    /**
     * How much padding on the bottom the texture has
     *
     * @return the amount of pixels of bottom padding
     */
    int getBottomPadding();

    /**
     * How much padding on the left the texture has
     *
     * @return the amount of pixels of left padding
     */
    int getLeftPadding();

    /**
     * How much padding on the right the texture has
     *
     * @return the amount of pixels of right padding
     */
    int getRightPadding();

    /**
     * The texture used for the previous page button
     *
     * @return a drawable for the previous page button
     */
    IDrawableLocationTextureHovereable getPrevPageTexture();

    /**
     * The texture used for the next page button
     *
     * @return a drawable for the next page button
     */
    IDrawableLocationTextureHovereable getNextPageTexture();

    /**
     * The texture used for the back button
     *
     * @return a drawable for the back button
     */
    IDrawableLocationTextureHovereable getBackTexture();

}
