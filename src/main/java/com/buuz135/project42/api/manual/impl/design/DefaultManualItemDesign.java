package com.buuz135.project42.api.manual.impl.design;

import com.buuz135.project42.api.manual.design.IManualItemDesign;

public class DefaultManualItemDesign implements IManualItemDesign {

    @Override
    public int getCoverColor() {
        return 0x499688;
    }

    @Override
    public int getBorderColor() {
        return 0x28554d;
    }

    @Override
    public int getLetterColor() {
        return 0x28554d;
    }
}
