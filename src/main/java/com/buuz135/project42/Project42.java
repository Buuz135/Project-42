package com.buuz135.project42;

import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.gui.GuiHandler;
import com.buuz135.project42.item.ItemManual;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.AnnotationHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = Project42.MOD_ID,
        name = Project42.MOD_NAME,
        version = Project42.VERSION
)
public class Project42 {

    public static final String MOD_ID = "project42";
    public static final String MOD_NAME = "Project42";
    public static final String VERSION = "1.0-SNAPSHOT";


    @Mod.Instance(MOD_ID)
    public static Project42 INSTANCE;

    public static Logger LOGGER;
    public static ItemManual TEST;
    public static CreativeTabs TAB = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TEST);
        }
    };

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) throws Exception {
        LOGGER = event.getModLog();
        LOGGER.info("Searching for manuals");
        for (Class<?> aClass : AnnotationHelper.getAnnotatedClasses(event.getAsmData(), ProjectManual.class)) {
            String id = aClass.getAnnotation(ProjectManual.class).value();
            if (!IManual.class.isAssignableFrom(aClass)) {
                throw new Exception("Manual with id " + id + " doesn't extend IManual");
            }
            if (!ManualInfo.MANUALS.containsKey(id)) {
                ManualInfo.MANUALS.put(id, new ManualInfo(id, (Class<? extends IManual>) aClass, aClass.getAnnotation(ProjectManual.class)));
                LOGGER.info("Registered Manual with id " + id + " successfully");
            } else {
                LOGGER.warn("Duplicate manual id " + id + ". IGNORED!");
            }
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ManualInfo.MANUALS.values().forEach(info -> {
            LOGGER.info("Sending category registering to " + info.getId());
            long time = System.currentTimeMillis();
            info.getManualObject().registerCategories(info);
            if (info.getCategorySize() == null) {
                int size = (int) Math.ceil(Math.sqrt(info.getCategories().size()));
                info.setCategorySize(size, size);
            }
            LOGGER.info("Registered " + info.getId() + "'s categories (" + info.getCategories().size() + ") in " + (System.currentTimeMillis() - time) + "ms...");
        });
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {

        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().register(TEST = new ItemManual("test"));
        }

    }
}
