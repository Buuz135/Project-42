package com.buuz135.project42;

import com.buuz135.project42.item.ItemManual;
import com.buuz135.project42.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    public static ItemManual MANUAL;
    @Mod.Instance(MOD_ID)
    public static Project42 INSTANCE;

    public static Logger LOGGER;
    public static CreativeTabs TAB = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(MANUAL);
        }
    };
    @SidedProxy(clientSide = "com.buuz135.project42.proxy.ClientProxy", serverSide = "com.buuz135.project42.proxy.CommonProxy")
    private static CommonProxy PROXY;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) throws Exception {
        LOGGER = event.getModLog();
        PROXY.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {

        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().register(MANUAL = new ItemManual());
        }

    }
}
