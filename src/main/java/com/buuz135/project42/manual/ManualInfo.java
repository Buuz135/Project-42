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

    public void registerCategory(IBookCategory category) {
        categories.add(category);
    }

    public List<IBookCategory> getCategories() {
        return categories;
    }

    public int getCategoryXSize() {
        return categoryXSize;
    }

    public void setCategoryXSize(int categoryXSize) {
        this.categoryXSize = categoryXSize;
    }

    public IManual getManualObject() {
        return manualObject;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public IManualDesign getDesign() {
        return design;
    }

    public void setDesign(IManualDesign design) {
        this.design = design;
    }

    public IManualItemDesign getManualItemDesign() {
        return manualItemDesign;
    }

    public void setManualItemDesign(IManualItemDesign manualItemDesign) {
        this.manualItemDesign = manualItemDesign;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getModName() {
        return modName;
    }
}
