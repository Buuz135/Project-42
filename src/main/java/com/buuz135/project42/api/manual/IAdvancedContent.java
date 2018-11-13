package com.buuz135.project42.api.manual;

import com.buuz135.project42.gui.GuiManualBase;
import net.minecraft.client.Minecraft;

/**
 * Makes the content advanced so it can add extra stuff like more buttons to the content when created
 */
public interface IAdvancedContent {

    /**
     * Called when content is added to a GUI
     *
     * @param mc   The current Minecraft instance
     * @param base The current GUI
     */
    void onAdded(Minecraft mc, GuiManualBase base);

    /**
     * Called when content is removed in a GUI
     *
     * @param mc   The current Minecraft instance
     * @param base The current GUI
     */
    void onRemoved(Minecraft mc, GuiManualBase base);
}
