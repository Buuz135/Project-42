package com.buuz135.project42.api.manual;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.tuple.Pair;

public interface IContent {

    boolean canBeSplitted();

    Pair<IContent, IContent> split(int y);

    int getSizeY();

    int getSizeX();

    void render(Minecraft mc, int x, int y, int mouseX, int mouseY);

}
