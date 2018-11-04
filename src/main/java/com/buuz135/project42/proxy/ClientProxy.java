package com.buuz135.project42.proxy;

import com.buuz135.project42.Project42;
import com.buuz135.project42.manual.ManualInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.buuz135.project42.Project42.LOGGER;
import static com.buuz135.project42.Project42.MANUAL;

@Mod.EventBusSubscriber(modid = Project42.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void modelRegistryEvent(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(MANUAL, 0, new ModelResourceLocation(MANUAL.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) throws Exception {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ManualInfo.MANUALS.values().forEach(info -> {
            LOGGER.info("Sending category registering to " + info.getId());
            long time = System.currentTimeMillis();
            info.getManualObject().registerCategories(info);
            if (info.getCategorySize() == null) {
                int size = (int) Math.ceil(Math.sqrt(info.getCategories().size()));
                info.setCategorySize(size, size);
            }
            int dimensionX = info.getDesign().getPageDesign().getTextureWidth() - info.getDesign().getPageDesign().getLeftPadding() - info.getDesign().getPageDesign().getRightPadding();
            int dimensionY = info.getDesign().getPageDesign().getTextureHeight() - info.getDesign().getPageDesign().getTopPadding() - info.getDesign().getPageDesign().getBottomPadding();
            info.getCategories().forEach(category -> category.getEntries().forEach((location, categoryEntry) -> categoryEntry.generatePages(dimensionX, dimensionY)));
            LOGGER.info("Registered " + info.getId() + "'s categories (" + info.getCategories().size() + ") in " + (System.currentTimeMillis() - time) + "ms...");
        });

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.hasTagCompound()) {
                NBTTagCompound compound = stack.getTagCompound();
                if (compound.hasKey("Id")) {
                    String id = compound.getString("Id");
                    if (ManualInfo.MANUALS.keySet().contains(id)) {
                        return ManualInfo.MANUALS.get(id).getManualItemColor();
                    }
                }
            }
            return 0xFFFFFF;
        }, MANUAL);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}