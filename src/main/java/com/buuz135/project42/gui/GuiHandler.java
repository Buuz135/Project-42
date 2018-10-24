package com.buuz135.project42.gui;

import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        List<String> values = new ArrayList<>(ManualInfo.MANUALS.keySet());
        if (ID < values.size()) {
            String manualID = values.get(ID);
            if (ManualInfo.MANUALS.containsKey(manualID)) {
                return new GuiCategoryList(null, ManualInfo.MANUALS.get(manualID));
            }

        }
        return null;
    }
}
