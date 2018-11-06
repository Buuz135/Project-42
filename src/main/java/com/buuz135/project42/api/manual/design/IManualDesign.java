package com.buuz135.project42.api.manual.design;

/**
 * Stores all the design elements of a manual
 * <p>
 * Example {@link com.buuz135.project42.api.manual.impl.design.DefaultManualDesign}
 */
public interface IManualDesign {

    /**
     * @return the design for the category GUI
     */
    IBackgroundDesign getCategoryDesign();

    /**
     *
     * @return the design for the category entry GUI
     */
    IBackgroundDesign getCategoryEntryDesign();

    /**
     *
     * @return the design for the pages GUI
     */
    IBackgroundDesign getPageDesign();

    /**
     *
     * @return the text color used for to display the name of the manual
     */
    int getDisplayColor();

    /**
     *
     * @return the text color used to display text in the manual
     */
    int getTextColor();

}
