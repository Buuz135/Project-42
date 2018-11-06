package com.buuz135.project42.util;

import com.buuz135.project42.gui.GuiManualBase;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ManualHelper {

    @SideOnly(Side.CLIENT)
    @Nullable
    public static ManualInfo getCurrentManualInfoFromGUI() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiManualBase) {
            return ((GuiManualBase) Minecraft.getMinecraft().currentScreen).getManualInfo();
        }
        return null;
    }

    @Nullable
    public static ManualInfo getManualInfoFromStack(ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound.hasKey("Id")) {
                String id = compound.getString("Id");
                if (ManualInfo.MANUALS.keySet().contains(id)) {
                    return ManualInfo.MANUALS.get(id);
                }
            }
        }
        return null;
    }

}
