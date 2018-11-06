package com.buuz135.project42.api.manual.impl.content;

import com.buuz135.project42.api.manual.IContent;
import com.buuz135.project42.util.ManualHelper;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class TextContent implements IContent {

    private List<String> splitContent;
    private int textWidth;

    public TextContent(String content, int textWidth) {
        this(Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(content, textWidth), textWidth);
    }

    public TextContent(List<String> strings, int textWidth) {
        this.splitContent = strings;
        this.textWidth = textWidth;
    }

    @Override
    public boolean canBeSplitted() {
        return true;
    }

    @Override
    public Pair<IContent, IContent> split(int y) {
        int line = y / Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
        return Pair.of(new TextContent(splitContent.subList(0, line), textWidth), new TextContent(splitContent.subList(line, splitContent.size()), textWidth));
    }

    @Override
    public int getSizeY() {
        return this.splitContent.size() * Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }

    @Override
    public int getSizeX() {
        return textWidth;
    }

    @Override
    public void render(Minecraft mc, int x, int y, int mouseX, int mouseY) {
        int amount = 0;
        int color = ManualHelper.getCurrentManualInfoFromGUI() != null ? ManualHelper.getCurrentManualInfoFromGUI().getDesign().getTextColor() : 0;
        for (String line : splitContent) {
            mc.fontRenderer.drawString(line, x, y + amount * mc.fontRenderer.FONT_HEIGHT, color, false);
            ++amount;
        }
    }

    @Override
    public void renderFront(Minecraft mc, int x, int y, int mouseX, int mouseY) {

    }

}
