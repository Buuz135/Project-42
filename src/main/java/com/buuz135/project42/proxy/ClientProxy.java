/*
 * This file is part of Project 42.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.project42.proxy;

import com.buuz135.project42.Project42;
import com.buuz135.project42.item.ItemManual;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.buuz135.project42.Project42.LOGGER;

@Mod.EventBusSubscriber(modid = Project42.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void modelRegistryEvent(ModelRegistryEvent event) {
        ManualInfo.MANUALS.forEach((s, info) -> ModelLoader.setCustomModelResourceLocation(info.getItemManual(), 0, new ModelResourceLocation(info.getItemManual().getModelLocation(), "inventory")));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) throws Exception {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ManualInfo.MANUALS.forEach((s, info) -> {
            if (info.getItemManual().getModelLocation().equals(ItemManual.MODEL_LOCATION)) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                    if (tintIndex == 1) return info.getManualItemDesign().getCoverColor();
                    if (tintIndex == 2) return info.getManualItemDesign().getBorderColor();
                    if (tintIndex == 3) return info.getManualItemDesign().getLetterColor();
                    return 0xFFFFFF;
                }, info.getItemManual());
            }
        });

        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
            ManualInfo.MANUALS.values().forEach(info -> {
                LOGGER.info("Sending category registering to " + info.getId());
                long time = System.currentTimeMillis();
                info.getCategories().clear();
                info.setLastManual(null);
                info.getManualObject().registerCategories(info);
                int dimensionX = info.getDesign().getPageDesign().getTextureWidth() - info.getDesign().getPageDesign().getLeftPadding() - info.getDesign().getPageDesign().getRightPadding();
                int dimensionY = info.getDesign().getPageDesign().getTextureHeight() - info.getDesign().getPageDesign().getTopPadding() - info.getDesign().getPageDesign().getBottomPadding();
                info.getCategories().forEach(category -> category.getEntries().forEach((location, categoryEntry) -> categoryEntry.generatePages(dimensionX, dimensionY)));
                LOGGER.info("Registered " + info.getId() + "'s categories (" + info.getCategories().size() + ") in " + (System.currentTimeMillis() - time) + "ms...");
            });
        });
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
