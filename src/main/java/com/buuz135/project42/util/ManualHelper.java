package com.buuz135.project42.util;

import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ManualHelper {

    @SideOnly(Side.CLIENT)
    @Nullable
    public static ManualInfo getCurrentManualInfo() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiManualBase) {
            return ((GuiManualBase) Minecraft.getMinecraft().currentScreen).getManualInfo();
        }
        return null;
    }

}
