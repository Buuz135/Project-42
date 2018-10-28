package com.buuz135.project42.api.manual;

import com.buuz135.project42.api.manual.design.display.ICategoryEntryDisplay;

import java.util.List;

public interface ICategoryEntry {

    List<IContent> getContent();

    ICategoryEntryDisplay getDisplay();
}
