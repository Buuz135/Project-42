package com.buuz135.project42.api.manual.design.display;

import net.minecraft.client.Minecraft;

public interface ICategoryEntryDisplay {

    void render(Minecraft mc, int x, int y, boolean isHovered);

    int sizeX();

    int sizeY();

    int getColor();
}
