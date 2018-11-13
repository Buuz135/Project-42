package com.buuz135.project42.api.manual;

import net.minecraft.client.Minecraft;

/**
 * Used to make {@link IContent} clickable
 */
public interface IClickable {

    /**
     * Method called when a content is clicked meaning that the mouse was inside the content when clicked
     *
     * @param mc          Current minecraft instance
     * @param mouseX      Current mouse X position value
     * @param mouseY      Current mouse Y position value
     * @param mouseAction Mouse action when clicked
     */
    void onClick(Minecraft mc, int mouseX, int mouseY, int mouseAction);

}
