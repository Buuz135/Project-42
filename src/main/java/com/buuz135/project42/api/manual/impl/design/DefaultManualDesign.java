package com.buuz135.project42.api.manual.impl.design;

import com.buuz135.project42.api.manual.design.IBackgroundDesign;
import com.buuz135.project42.api.manual.design.IDrawableTexture;
import com.buuz135.project42.api.manual.design.IManualDesign;

public class DefaultManualDesign implements IManualDesign {

    public static DefaultBackgroundDesign BG_DESIGN = new DefaultBackgroundDesign();

    @Override
    public IBackgroundDesign getCategoryDesign() {
        return BG_DESIGN;
    }

    @Override
    public int getDisplayColor() {
        return 0x00FFFF;
    }

    @Override
    public int getTextColor() {
        return 0x42a1f4;
    }

    @Override
    public IBackgroundDesign getCategoryEntryDesign() {
        return BG_DESIGN;
    }

    @Override
    public IBackgroundDesign getPageDesign() {
        return BG_DESIGN;
    }

    @Override
    public IDrawableTexture getPrevPageTexture() {
        return null;
    }

    @Override
    public IDrawableTexture getNextPageTexture() {
        return null;
    }

    @Override
    public IDrawableTexture getBackTexture() {
        return null;
    }

}
