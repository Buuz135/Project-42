package com.buuz135.project42.api.manual;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents any type of content that later can be formatted into pages
 * <p>
 * Examples: {@link com.buuz135.project42.api.manual.impl.content.TextContent} and {@link com.buuz135.project42.api.manual.impl.content.RecipeContent}
 */
public interface IContent {

    /**
     * Gets if IContent can bit splitted into 2 different contents
     * @return true if it can be splitted or false if it can't be
     */
    boolean canBeSplitted();

    /**
     * If it can splitted it splits the current content into two new ones based on a length value
     * @param y the length value from where to split the two contents
     * @return a pair of contents where the left value is the top part of the content and the right value is the bottom part of the content
     */
    Pair<IContent, IContent> split(int y);

    /**
     * Gets the length of the content
     * @return how long is the content (in pixels)
     */
    int getSizeY();

    /**
     * Gets the width of the content
     * @return how wide is the content (in pixels)
     */
    int getSizeX();

    /**
     * Renders the back of the content, method run before "renderFront"
     *
     * @param mc     Current minecraft instance
     * @param x      The X position of the content
     * @param y      The Y position of the content
     * @param mouseX The X position of the mouse
     * @param mouseY The Y position of the mouse
     */
    void renderBack(Minecraft mc, int x, int y, int mouseX, int mouseY);

    /**
     * Renders the front of the content, method run after "renderBack"
     * @param mc Current minecraft instance
     * @param x The X position of the content
     * @param y The Y position of the content
     * @param mouseX The X position of the mouse
     * @param mouseY The Y position of the mouse
     */
    void renderFront(Minecraft mc, int x, int y, int mouseX, int mouseY);
}
