package com.buuz135.project42.api.manual;

import com.buuz135.project42.manual.ManualInfo;

/**
 * Interface used with the annotation {@link com.buuz135.project42.api.annotation.ProjectManual} to register a manual
 */
public interface IManual {

    /**
     * Registers the categories and the contents of the manual
     *
     * @param info All the manual information where the categories are added
     */
    void registerCategories(ManualInfo info);
}
