package com.buuz135.project42.manual;

import com.buuz135.project42.api.manual.IBookCategory;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.api.manual.design.IManualDesign;
import com.buuz135.project42.api.manual.design.IManualItemDesign;
import com.buuz135.project42.api.manual.impl.design.DefaultManualDesign;
import com.buuz135.project42.api.manual.impl.design.DefaultManualItemDesign;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ManualInfo {

    public static LinkedHashMap<String, ManualInfo> MANUALS = new LinkedHashMap<>();

    private final String id;
    private final Class<? extends IManual> manualClass;
    private final List<IBookCategory> categories;
    private IManual manualObject;
    private int categoryXSize;
    private String displayName;
    private IManualDesign design;
    private IManualItemDesign manualItemDesign;
    private String modName;

    public ManualInfo(String id, String modName, Class<? extends IManual> manualClass) throws IllegalAccessException, InstantiationException {
        this.id = id;
        this.manualClass = manualClass;
        this.categories = new ArrayList<>();
        this.manualObject = manualClass.newInstance();
        this.displayName = "";
        this.design = new DefaultManualDesign();
        this.manualItemDesign = new DefaultManualItemDesign();
        this.modName = modName;
        this.categoryXSize = 0;
    }

    /**
     * Registers a category into the manual
     *
     * @param category A category to register
     */
    public void registerCategory(IBookCategory category) {
        categories.add(category);
    }

    /**
     * Gets all the registered categories
     * @return a list of categories
     */
    public List<IBookCategory> getCategories() {
        return categories;
    }

    /**
     * Gets how many categories per row can be shown in the category GUI
     * @return The category per row amount
     */
    public int getCategoryXSize() {
        return categoryXSize;
    }

    /**
     * Sets how many categories per row can be shown in the category GUI
     * @param categoryXSize The category per row amount
     */
    public void setCategoryXSize(int categoryXSize) {
        this.categoryXSize = categoryXSize;
    }

    /**
     * Gets the IManual object
     * @return the manual object
     */
    public IManual getManualObject() {
        return manualObject;
    }

    /**
     * Gets the id of the manual
     * @return the id of the manual
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the display name of the manual
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name of the manual (It can be a localization string)
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the current manual design
     * @return the manual design
     */
    public IManualDesign getDesign() {
        return design;
    }

    /**
     * Sets the manual design
     * @param design the manual design
     */
    public void setDesign(IManualDesign design) {
        this.design = design;
    }

    /**
     * Gets the design for the item manual
     * @return the manual item design
     */
    public IManualItemDesign getManualItemDesign() {
        return manualItemDesign;
    }

    /**
     * Sets the design of the item manual
     * @param manualItemDesign the manual item design
     */
    public void setManualItemDesign(IManualItemDesign manualItemDesign) {
        this.manualItemDesign = manualItemDesign;
    }

    /**
     * Gets the mod name that adds the manual
     * @return the mod name
     */
    public String getModName() {
        return modName;
    }
}
