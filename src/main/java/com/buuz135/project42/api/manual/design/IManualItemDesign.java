package com.buuz135.project42.api.manual.design;

/**
 * Stores information about the manual design colors
 * <p>
 * Example: {@link com.buuz135.project42.api.manual.impl.design.DefaultManualItemDesign}
 */
public interface IManualItemDesign {

    /**
     * @return the color of the cover
     */
    int getCoverColor();

    /**
     * @return the color of the border
     */
    int getBorderColor();

    /**
     * @return the color of the letter details
     */
    int getLetterColor();

}
