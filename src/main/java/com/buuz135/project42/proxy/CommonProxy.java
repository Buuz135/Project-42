package com.buuz135.project42.proxy;

import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.AnnotationHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.buuz135.project42.Project42.LOGGER;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) throws Exception {
        LOGGER.info("Searching for manuals");
        for (Class<?> aClass : AnnotationHelper.getAnnotatedClasses(event.getAsmData(), ProjectManual.class)) {
            String id = aClass.getAnnotation(ProjectManual.class).value();
            if (!IManual.class.isAssignableFrom(aClass)) {
                throw new Exception("Manual with id " + id + " doesn't extend IManual");
            }
            if (!ManualInfo.MANUALS.containsKey(id)) {
                ManualInfo.MANUALS.put(id, new ManualInfo(id, aClass.getAnnotation(ProjectManual.class).modName(), (Class<? extends IManual>) aClass));
                LOGGER.info("Registered Manual with id " + id + " successfully");
            } else {
                LOGGER.warn("Duplicate manual id " + id + ". IGNORED!");
            }
        }
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
